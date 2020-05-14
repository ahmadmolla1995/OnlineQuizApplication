package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;


@Entity
public class QuestionProperty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionID;
    private Long examID;
    private Double grade;


    public QuestionProperty(Long id, Long questionID, Long examID, Double grade) {
        this.id = id;
        this.questionID = questionID;
        this.examID = examID;
        this.grade = grade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) { this.grade = grade; }
}
