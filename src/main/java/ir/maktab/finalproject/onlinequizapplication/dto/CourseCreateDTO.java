package ir.maktab.finalproject.onlinequizapplication.dto;


public class CourseCreateDTO {
    private Long courseCode;
    private String title;
    private String startDate;
    private String endDate;
    private String courseStatus;


    public CourseCreateDTO(Long courseCode, String title, String startDate, String endDate, String courseStatus) {
        this.courseCode = courseCode;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseStatus = courseStatus;
    }


    public Long getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCourseStatus() { return courseStatus; }
}
