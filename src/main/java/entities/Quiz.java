package entities;

import javax.persistence.*;
import java.util.List;

@Table(name = "quiz")
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_points")
    private Long totalPoints;

    @Column(name = "total_correct")
    private Long totalCorrect;

    @Column(name = "total_incorrect")
    private Long totalIncorrect;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="quiz")
    private List<Question> questions;

    @ManyToOne()
    @JoinColumn(name = "username")
    private User user;


    public Quiz(List<Question> questions, User user) {
        this.totalPoints = 0L;
        this.totalCorrect = 0L;
        this.totalIncorrect = 0L;
        this.questions = questions;
        this.user = user;
    }

    public Quiz(Long totalPoints, Long totalCorrect, Long totalIncorrect, List<Question> questions, User user) {
        this.totalPoints = totalPoints;
        this.totalCorrect = totalCorrect;
        this.totalIncorrect = totalIncorrect;
        this.questions = questions;
        this.user = user;
    }

    public Quiz() {}

    public void setId(Long id) {this.id = id;}
    public void setTotalPoints(Long totalPoints) {this.totalPoints = totalPoints;}
    public void setTotalCorrect(Long totalCorrect) {this.totalCorrect = totalCorrect;}
    public void setTotalIncorrect(Long totalIncorrect) {this.totalIncorrect = totalIncorrect;}
    public void setQuestions(List<Question> questions) {this.questions = questions;}
    public void addQuestion(Question question) {this.questions.add(question);}
    public void setUser(User user) {this.user = user;}

    public Long getId() {return id;}
    public Long getTotalPoints() {return totalPoints;}
    public Long getTotalCorrect() {return totalCorrect;}
    public Long getTotalIncorrect() {return totalIncorrect;}
    public List<Question> getQuestions() {return questions;}
    public User getUser() {return user;}

}