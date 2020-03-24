package ir.maktab.finalproject.onlinequizapplication.dto;


public class RemovePersonFromCourseDTO {
    private Long courseID;
    private Long personID;
    private String roleType;


    public RemovePersonFromCourseDTO(Long courseID, Long personID, String roleType) {
        this.courseID = courseID;
        this.personID = personID;
        this.roleType = roleType;
    }

    public Long getCourseID() {
        return courseID;
    }

    public Long getPersonID() {
        return personID;
    }

    public String getRoleType() {
        return roleType;
    }
}
