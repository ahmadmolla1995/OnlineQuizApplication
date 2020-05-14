package ir.maktab.finalproject.onlinequizapplication.dto;


public class EditQuestionDTO {
    private Long questionID;
    private Long examID;
    private String title;
    private String problemDescription;
    private Double grade;

    public EditQuestionDTO(Long questionID, Long examID , String title, String problemDescription, Double grade) {
        this.questionID = questionID;
        this.examID = examID;
        this.title = title;
        this.problemDescription = problemDescription;
        this.grade = grade;
    }


    public Long getQuestionID() { return questionID; }

    public Long getExamID() { return examID; }

    public String getTitle() {
        return title;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public Double getGrade() {
        return grade;
    }
}
