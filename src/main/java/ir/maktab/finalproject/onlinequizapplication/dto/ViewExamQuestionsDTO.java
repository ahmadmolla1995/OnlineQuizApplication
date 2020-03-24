package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewExamQuestionsDTO {
    private Long examID;

    public ViewExamQuestionsDTO(Long examID) {
        this.examID = examID;
    }

    public Long getExamID() {
        return examID;
    }
}
