package ir.maktab.finalproject.onlinequizapplication.dto;


public class AddStudentDTO {
    private Long courseID;
    private Long studentID;

    public AddStudentDTO(Long courseID, Long studentID) {
        this.courseID = courseID;
        this.studentID = studentID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public Long getStudentID() {
        return studentID;
    }
}
