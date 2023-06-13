package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Flag;
import entities.Question;
import entities.Quiz;
import entities.User;
import errorhandling.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class QuizFacade {
    private static QuizFacade instance;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //Private Constructor to ensure Singleton
    private QuizFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static QuizFacade getQuizFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new QuizFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static String fetchData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json"); // Add this line
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Error in fetchData");
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public Quiz create(Quiz quiz) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(quiz);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return quiz;
    }

    public Quiz update(Quiz quiz) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Quiz found = em.find(Quiz.class, quiz.getId());
        if (found == null) {
            throw new NotFoundException("Entity with ID: " + quiz.getId() + " not found");
        }

        // TODO: update values here

        try {
            em.getTransaction().begin();
            Quiz updated = em.merge(quiz);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    public Quiz delete(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Quiz found = em.find(Quiz.class, id);
        if (found == null) {
            throw new NotFoundException("Could not remove Entity with id: " + id);
        }
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            return found;
        } finally {
            em.close();
        }
    }

    public Quiz getById(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Quiz quiz;
        try {
            quiz = em.find(Quiz.class, id);
            if (quiz == null) {
                throw new NotFoundException();
            }
        } finally {
            em.close();
        }
        return quiz;
    }


    public List<Quiz> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Quiz> query = em.createQuery("SELECT z FROM Quiz z", Quiz.class);
        return query.getResultList();
    }

    public Quiz generateQuiz(String username) throws NotFoundException {
        User user = UserFacade.getUserFacade(emf).getUserByName(username);
        EntityManager em = emf.createEntityManager();

        //flags
        TypedQuery<Flag> query;
        query = em.createQuery("SELECT f FROM Flag f ", Flag.class);
        List<Flag> allFlags = query.getResultList();
        Collections.shuffle(allFlags);

        //questions
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 10 questions
            Flag correctFlag = allFlags.get(i); // pick a random flag as the correct answer
            List<Flag> newFlagsList = new ArrayList<>(allFlags);
            newFlagsList.remove(correctFlag);  //
            Collections.shuffle(newFlagsList);// all flags except the correct one

            //answers (3 wrong and 1 correct) correct is at index 0
            List<String> answers = new ArrayList<>();
            answers.add(correctFlag.getCountryName());
            answers.add(newFlagsList.get(0).getCountryName());
            answers.add(newFlagsList.get(1).getCountryName());
            answers.add(newFlagsList.get(2).getCountryName());
            Collections.shuffle(answers);

            //creating the question entity
            Question question = new Question(
                    correctFlag.getId(),
                    correctFlag.getFlagSVG(),
                    answers.get(0),
                    answers.get(1),
                    answers.get(2),
                    answers.get(3),
                    0L);
            questions.add(question);
        }
        return new Quiz(questions, user);
    }

    public Long getResult(Long correctId, String answer, float time) throws NotFoundException {
        Flag flag = FlagFacade.getFlagFacade(emf).getById(correctId);
        if (!flag.getCountryName().equals(answer)) {
            return 0L;
        } else {
            int points = (int) Math.floor(time * 100);
            return (long) points;
        }
    }
}

