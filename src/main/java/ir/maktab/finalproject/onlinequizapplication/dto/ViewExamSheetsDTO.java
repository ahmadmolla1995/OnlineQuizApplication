package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewExamSheetsDTO {
    private Long examID;

    public ViewExamSheetsDTO(Long examID) {
        this.examID = examID;
    }

    public Long getExamID() {
        return examID;
    }
}
