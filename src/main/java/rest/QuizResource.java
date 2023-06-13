package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FlagDTO;
import dtos.QuestionDTO;
import dtos.QuizDTO;
import entities.Flag;
import entities.Question;
import entities.Quiz;
import entities.User;
import errorhandling.NotFoundException;
import facades.FlagFacade;
import facades.QuestionFacade;
import facades.QuizFacade;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("quiz")
public class QuizResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final QuizFacade quizFacade = QuizFacade.getQuizFacade(EMF);
    private static final FlagFacade flagFacade = FlagFacade.getFlagFacade(EMF);
    private static final QuestionFacade questionFacade = QuestionFacade.getQuestionFacade(EMF);
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"quiz endpoint\"}";
    }

    @Path("generate/{username}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response generate(@PathParam("username") String username) throws NotFoundException {
        Quiz quiz = quizFacade.generateQuiz(username);
        return Response.ok().entity(GSON.toJson(new QuizDTO(quiz))).build();
    }
    private Quiz calcQuizScore(List<QuestionDTO> questionsDTO, User user) {
        Long totalPoints = 0L;
        Long totalCorrect = 0L;
        Long totalIncorrect = 0L;
        List<Question> questions = new ArrayList<>();
        for (QuestionDTO q : questionsDTO) {
            questions.add(new Question(
                    q.getCorrectCountryId(),
                    q.getFlagSVG(),
                    q.getAnswer1(),
                    q.getAnswer2(),
                    q.getAnswer3(),
                    q.getAnswer4(),
                    q.getPoints()
            ));
            totalPoints += q.getPoints();
            if (q.getPoints() != 0) {
                totalCorrect++;
            } else {
                totalIncorrect++;
            }
        }
        return new Quiz(totalPoints, totalCorrect, totalIncorrect, questions, user);
    }

    @Path("create")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String jsonContext) throws NotFoundException {
        QuizDTO dto = GSON.fromJson(jsonContext, QuizDTO.class);
        User user = userFacade.getUserByName(dto.getUsername());
        Quiz temp = calcQuizScore(dto.getQuestions(), user);
        Quiz created = quizFacade.create(temp);

        for (Question q : created.getQuestions()) {
            q.setQuiz(created);
            questionFacade.update(q); // makes relation between question(20) and quiz(1)

            Flag flag = flagFacade.getById(q.getCorrectCountryId());
            flag.setAnswered(flag.getAnswered() + 1L);
            if (q.getPoints() > 0) {
                flag.setCorrect(flag.getCorrect() + 1L);
            } else {
                flag.setIncorrect(flag.getIncorrect() + 1L);
            }
            flagFacade.update(flag);
        }
        QuizDTO createdDTO = new QuizDTO(created);
        return Response.ok().entity(GSON.toJson(createdDTO)).build();
    }

    @Path("result/{correctId}/{answer}/{time}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Long getResult(@PathParam("correctId") Long correctId, @PathParam("answer") String answer, @PathParam("time") float time) throws NotFoundException {
        return quizFacade.getResult(correctId, answer, time);
    }

}