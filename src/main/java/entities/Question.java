package entities;

import javax.persistence.*;

@Table(name = "question")
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "correct_country_id")
    private Long correctCountryId;

    @Column(name = "country_svg")
    private String flagSVG;

    @Column(name = "answer1")
    private String answer1;

    @Column(name = "answer2")
    private String answer2;

    @Column(name = "answer3")
    private String answer3;

    @Column(name = "answer4")
    private String answer4;

    @Column(name = "points")
    private Long points;

    @ManyToOne()
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question() {}


    public Question(Long correctCountryId, String flagSVG, String answer1, String answer2, String answer3, String answer4, Long points) {
        this.correctCountryId = correctCountryId;
        this.flagSVG = flagSVG;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.points = points;
    }

    public void setId(Long id) {this.id = id;}
    public void setCorrectCountryId(Long correctCountryId) {this.correctCountryId = correctCountryId;}
    public void setFlagSVG(String flagSVG) {this.flagSVG = flagSVG;}
    public void setAnswer1(String answer1) {this.answer1 = answer1;}
    public void setAnswer2(String answer2) {this.answer2 = answer2;}
    public void setAnswer3(String answer3) {this.answer3 = answer3;}
    public void setAnswer4(String answer4) {this.answer4 = answer4;}
    public void setPoints(Long points) {this.points = points;}
    public void setQuiz(Quiz quiz) {this.quiz = quiz;}

    public Long getId() {return id;}
    public Long getCorrectCountryId() {return correctCountryId;}
    public String getflagSVG() {return flagSVG;}
    public String getAnswer1() {return answer1;}
    public String getAnswer2() {return answer2;}
    public String getAnswer3() {return answer3;}
    public String getAnswer4() {return answer4;}
    public Long getPoints() {return points;}
    public Quiz getQuiz() {return quiz;}
}