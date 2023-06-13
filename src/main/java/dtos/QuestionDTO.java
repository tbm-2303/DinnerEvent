package dtos;

import entities.Question;

public class QuestionDTO {
    private Long id;
    private Long correctCountryId;
    private String flagSVG;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private Long points;

    public QuestionDTO(Long correctCountryId, String flagSVG, String answer1, String answer2, String answer3, String answer4, Long points) {
        this.correctCountryId = correctCountryId;
        this.flagSVG = flagSVG;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.points = points;
    }

    public QuestionDTO(Question q) {
        if (q.getId() != null) {
            this.id = q.getId();
        }
        this.correctCountryId = q.getCorrectCountryId();
        this.flagSVG = q.getflagSVG();
        this.answer1 = q.getAnswer1();
        this.answer2 = q.getAnswer2();
        this.answer3 = q.getAnswer3();
        this.answer4 = q.getAnswer4();
        this.points = q.getPoints();
    }

    public void setId(Long id) {this.id = id;}
    public void setCorrectCountryId(Long correctCountryId) {this.correctCountryId = correctCountryId;}
    public void setFlagSVG(String flagSVG) {this.flagSVG = flagSVG;}
    public void setAnswer1(String answer1) {this.answer1 = answer1;}
    public void setAnswer2(String answer2) {this.answer2 = answer2;}
    public void setAnswer3(String answer3) {this.answer3 = answer3;}
    public void setAnswer4(String answer4) {this.answer4 = answer4;}
    public void setPoints(Long points) {this.points = points;}

    public Long getId() {return id;}
    public Long getCorrectCountryId() {return correctCountryId;}
    public String getFlagSVG() {return flagSVG;}
    public String getAnswer1() {return answer1;}
    public String getAnswer2() {return answer2;}
    public String getAnswer3() {return answer3;}
    public String getAnswer4() {return answer4;}
    public Long getPoints() {return points;}
}
