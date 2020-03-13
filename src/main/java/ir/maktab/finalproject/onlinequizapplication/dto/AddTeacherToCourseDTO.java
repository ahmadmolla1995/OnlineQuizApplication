package ir.maktab.finalproject.onlinequizapplication.dto;


public class AddTeacherToCourseDTO {
    private Long courseID;
    private Long teacherID;

    public AddTeacherToCourseDTO(Long courseID, Long teacherID) {
        this.courseID = courseID;
        this.teacherID = teacherID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public Long getTeacherID() {
        return teacherID;
    }
}
