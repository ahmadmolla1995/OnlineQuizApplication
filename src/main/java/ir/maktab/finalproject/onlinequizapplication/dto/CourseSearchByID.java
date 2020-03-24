package ir.maktab.finalproject.onlinequizapplication.dto;


public class CourseSearchByID {
    private Long courseID;

    public CourseSearchByID(Long courseID) {
        this.courseID = courseID;
    }

    public Long getCourseID() {
        return courseID;
    }
}
