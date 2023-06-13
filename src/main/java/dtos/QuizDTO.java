package dtos;

import entities.Question;
import entities.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDTO {
    private Long id;
    private Long totalPoints;
    private Long totalCorrect;
    private Long totalIncorrect;
    private List<QuestionDTO> questions = new ArrayList<>();
    private String continentName;
    private String username;

    public QuizDTO(Long totalPoints, Long totalCorrect, Long totalIncorrect, List<QuestionDTO> questions, String continentName, String username) {
        this.totalPoints = totalPoints;
        this.totalCorrect = totalCorrect;
        this.totalIncorrect = totalIncorrect;
        this.questions = questions;
        this.continentName = continentName;
        this.username = username;
    }

    public QuizDTO(Quiz q) {
        if (q.getId() != null) {
            this.id = q.getId();
        }
        this.totalPoints = q.getTotalPoints();
        this.totalCorrect = q.getTotalCorrect();
        this.totalIncorrect = q.getTotalIncorrect();
        for (Question question : q.getQuestions()) {
            this.questions.add(new QuestionDTO(question));
        }
        this.username = q.getUser().getUserName();
    }

    public void setId(Long id) {this.id = id;}
    public void setTotalPoints(Long totalPoints) {this.totalPoints = totalPoints;}
    public void setTotalCorrect(Long totalCorrect) {this.totalCorrect = totalCorrect;}
    public void setTotalIncorrect(Long totalIncorrect) {this.totalIncorrect = totalIncorrect;}
    public void setQuestions(List<QuestionDTO> questions) {this.questions = questions;}
    public void setContinentName(String continentName) {this.continentName = continentName;}
    public void setUsername(String username) {this.username = username;}

    public Long getId() {return id;}
    public Long getTotalPoints() {return totalPoints;}
    public Long getTotalCorrect() {return totalCorrect;}
    public Long getTotalIncorrect() {return totalIncorrect;}
    public List<QuestionDTO> getQuestions() {return questions;}
    public String getContinentName() {return continentName;}
    public String getUsername() {return username;}
}
