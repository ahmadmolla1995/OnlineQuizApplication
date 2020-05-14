package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewCourseExamsDTO {
    private Long courseID;
    private Long studentID;

    public ViewCourseExamsDTO(Long courseID, Long studentID) {
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
