package ir.maktab.finalproject.onlinequizapplication.model;

import ir.maktab.finalproject.onlinequizapplication.enumeration.CourseStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseCode;
    private String title;
    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @ManyToOne
    private Teacher teacher = new Teacher();

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();


    public Course() {}

    public Course(Long id, Long courseCode, String title, Date startDate, Date endDate, CourseStatus courseStatus) {
        this.id = id;
        this.courseCode = courseCode;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseStatus = courseStatus;
    }


    public Long getId() {
        return id;
    }

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
