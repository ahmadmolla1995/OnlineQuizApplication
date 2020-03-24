package ir.maktab.finalproject.onlinequizapplication.dto;


public class ViewArchivedTestsDTO {
    private Long courseID;
    private Long examID;

    public ViewArchivedTestsDTO(Long courseID, Long examID) {
        this.courseID = courseID;
        this.examID = examID;
    }

    public Long getCourseID() {
        return courseID;
    }

    public Long getExamID() {
        return examID;
    }
}
