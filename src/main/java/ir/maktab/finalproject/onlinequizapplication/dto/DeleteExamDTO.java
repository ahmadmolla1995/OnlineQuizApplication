package ir.maktab.finalproject.onlinequizapplication.dto;


public class DeleteExamDTO {
    private Long examID;

    public DeleteExamDTO(Long examID) {
        this.examID = examID;
    }

    public Long getExamID() {
        return examID;
    }
}
