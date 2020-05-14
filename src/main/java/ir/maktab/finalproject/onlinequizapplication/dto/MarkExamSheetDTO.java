package ir.maktab.finalproject.onlinequizapplication.dto;


public class MarkExamSheetDTO {
    private Long examSheetID;
    private Long questionItemID;
    private Double grade;

    public MarkExamSheetDTO(Long examSheetID, Long questionItemID, Double grade) {
        this.examSheetID = examSheetID;
        this.questionItemID = questionItemID;
        this.grade = grade;
    }


    public Long getExamSheetID() {
        return examSheetID;
    }

    public Long getQuestionItemID() {
        return questionItemID;
    }

    public Double getGrade() {
        return grade;
    }
}
