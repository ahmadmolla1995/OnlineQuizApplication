package ir.maktab.finalproject.onlinequizapplication.dto;


public class StartExamDTO {
    private Long examID;
    private Long studentID;

    public StartExamDTO(Long examID, Long studentID) {
        this.examID = examID;
        this.studentID = studentID;
    }


    public Long getExamID() {
        return examID;
    }

    public Long getStudentID() {
        return studentID;
    }
}
