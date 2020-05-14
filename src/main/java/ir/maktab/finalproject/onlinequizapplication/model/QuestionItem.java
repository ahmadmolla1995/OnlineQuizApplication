package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;


@Entity
public class QuestionItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionID;
    private String problemDescription;
    private String response;
    private Double obtainedScore;

    @ManyToOne
    private ExamSheet examSheet;


    public QuestionItem () {}

    public QuestionItem(Long id, Long questionID, String problemDescription) {
        this.id = id;
        this.questionID = questionID;
        this.problemDescription = problemDescription;
        this.response = "";
    }


    public Long getId() {
        return id;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public String getProblemDescription() { return problemDescription; }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setExamSheet(ExamSheet examSheet) { this.examSheet = examSheet; }

    public ExamSheet getExamSheet() { return examSheet; }

    public void setObtainedScore(Double obtainedScore) { this.obtainedScore = obtainedScore; }

    public Double getObtainedScore() { return obtainedScore; }
}
