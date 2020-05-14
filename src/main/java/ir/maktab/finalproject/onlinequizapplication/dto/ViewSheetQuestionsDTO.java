package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewSheetQuestionsDTO {
    private Long examSheetID;

    public ViewSheetQuestionsDTO(Long examSheetID) {
        this.examSheetID = examSheetID;
    }

    public Long getExamSheetID() {
        return examSheetID;
    }
}
