package ir.maktab.finalproject.onlinequizapplication.dto;


public class CreateExamDTO {
    private Long courseID;
    private String title;
    private String description;
    private String duration;


    public CreateExamDTO(Long courseID, String title, String description, String duration) {
        this.courseID = courseID;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Long getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }
}
