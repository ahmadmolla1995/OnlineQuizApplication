package ir.maktab.finalproject.onlinequizapplication.model;

import ir.maktab.finalproject.onlinequizapplication.enumeration.ExamSheetSubmissionStatus;

import javax.persistence.*;
import java.util.*;


@Entity
public class ExamSheet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentID;
    private Double obtainedScore;

    @Enumerated(EnumType.STRING)
    private ExamSheetSubmissionStatus submissionStatus;

    @ManyToOne
    private Exam exam;

    @OneToMany(mappedBy = "examSheet")
    private List<QuestionItem> questionItems = new ArrayList<>();


    public ExamSheet() {}

    public ExamSheet(Long id, Long studentID) {
        this.id = id;
        this.studentID = studentID;
        this.obtainedScore = 0.0;
        this.submissionStatus = ExamSheetSubmissionStatus.NotSubmitted;
    }


    public Long getId() {
        return id;
    }

    public Long getStudentID() {
        return studentID;
    }

    public Double getObtainedScore() {
        return obtainedScore;
    }

    public void setObtainedScore() {
        obtainedScore = 0.0;
        for (QuestionItem q: questionItems)
            if (q.getObtainedScore() != null)
                obtainedScore += q.getObtainedScore();
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void addQuestionItem(QuestionItem questionItem) { this.questionItems.add(questionItem); }

    public List<QuestionItem> getQuestionItems() { return questionItems; }

    public void setSubmissionStatus(ExamSheetSubmissionStatus submissionStatus) { this.submissionStatus = submissionStatus; }

    public ExamSheetSubmissionStatus getSubmissionStatus() { return submissionStatus; }
}
