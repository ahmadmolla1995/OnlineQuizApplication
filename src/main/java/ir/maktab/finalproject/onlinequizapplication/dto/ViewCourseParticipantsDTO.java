package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewCourseParticipantsDTO {
    private Long courseID;

    public ViewCourseParticipantsDTO(Long courseID) {
        this.courseID = courseID;
    }

    public Long getCourseID() {
        return courseID;
    }
}
