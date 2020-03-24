package ir.maktab.finalproject.onlinequizapplication.dto;


public class AddArchivedQuestionToExamDTO {
    private Long courseID;
    private Long examID;
    private Long questionID;


    public AddArchivedQuestionToExamDTO(Long courseID, Long examID, Long questionID) {
        this.courseID = courseID;
        this.examID = examID;
        this.questionID = questionID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public Long getExamID() {
        return examID;
    }

    public Long getQuestionID() {
        return questionID;
    }
}
