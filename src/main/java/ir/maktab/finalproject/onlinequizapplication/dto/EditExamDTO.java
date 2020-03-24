package ir.maktab.finalproject.onlinequizapplication.dto;


public class EditExamDTO {
    private Long examID;
    private String title;
    private String description;
    private Integer duration;

    public EditExamDTO(Long examID, String title, String description, Integer duration) {
        this.examID = examID;
        this.title = title;
        this.description = description;
        this.duration = duration;
    }


    public Long getExamID() {
        return examID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDuration() {
        return duration;
    }
}
