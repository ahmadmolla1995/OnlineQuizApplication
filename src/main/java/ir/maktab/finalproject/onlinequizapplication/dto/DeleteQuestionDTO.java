package ir.maktab.finalproject.onlinequizapplication.dto;


public class DeleteQuestionDTO {
    private Long examID;
    private Long questionID;

    public DeleteQuestionDTO(Long examID, Long questionID) {
        this.examID = examID;
        this.questionID = questionID;
    }


    public Long getExamID() {
        return examID;
    }

    public Long getQuestionID() {
        return questionID;
    }
}
