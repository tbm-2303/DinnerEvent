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
public class QuestionFacade {
    private static QuestionFacade instance;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //Private Constructor to ensure Singleton
    private QuestionFacade() {
    }

    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static QuestionFacade getQuestionFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new QuestionFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Question create(Question question) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(question);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return question;
    }


    public Question update(Question question) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Question found = em.find(Question.class, question.getId());
        if (found == null) {
            throw new NotFoundException("Entity with ID: " + question.getId() + " not found");
        }
        // TODO: update values here
        try {
            em.getTransaction().begin();
            Question updated = em.merge(question);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }


    public Question delete(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Question found = em.find(Question.class, id);
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

    public Question getById(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Question question;
        try {
            question = em.find(Question.class, id);
            if (question == null) {
                throw new NotFoundException();
            }
        } finally {
            em.close();
        }
        return question;
    }

    public List<Question> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Question> query = em.createQuery("SELECT z FROM Question z", Question.class);
        return query.getResultList();
    }




}

