package ir.maktab.finalproject.onlinequizapplication.dto;


public class AddMultipleChoiceQuestionDTO {
    private String title;
    private String problemDescription;
    private Double grade;
    private Long examID;
    private String options;
    private Long correctResponse;

    public AddMultipleChoiceQuestionDTO(String title, String problemDescription, Double grade, Long examID, String options, Long correctResponse) {
        this.title = title;
        this.problemDescription = problemDescription;
        this.grade = grade;
        this.examID = examID;
        this.options = options;
        this.correctResponse = correctResponse;
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

    public String getOptions() {
        return options;
    }

    public Long getCorrectResponse() {
        return correctResponse;
    }
}
