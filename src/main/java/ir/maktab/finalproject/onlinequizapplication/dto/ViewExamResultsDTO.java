package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewExamResultsDTO {
    private Long examID;

    public ViewExamResultsDTO(Long examID) {
        this.examID = examID;
    }

    public Long getExamID() {
        return examID;
    }
}
