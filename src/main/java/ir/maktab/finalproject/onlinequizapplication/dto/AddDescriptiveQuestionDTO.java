package ir.maktab.finalproject.onlinequizapplication.dto;


public class AddDescriptiveQuestionDTO {
    private String title;
    private String problemDescription;
    private Double grade;
    private Long examID;

    public AddDescriptiveQuestionDTO(String title, String problemDescription, Double grade, Long examID) {
        this.title = title;
        this.problemDescription = problemDescription;
        this.grade = grade;
        this.examID = examID;
    }


    public String getTitle() {
        return title;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public Double getGrade() {
        return grade;
    }

    public Long getExamID() {
        return examID;
    }
}
