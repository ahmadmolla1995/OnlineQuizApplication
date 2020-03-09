package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Student extends Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentCode;

    @ManyToMany
    @JoinTable(name = "students_courses", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;


    public Student() {}

    public Student(Long id, String firstName, String lastName, String education, String email, String homeNumber, String cellPhoneNumber, String address, Long studentCode) {
        super(id, firstName, lastName, education, email, homeNumber, cellPhoneNumber, address);
        this.studentCode = studentCode;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Long studentCode) {
        this.studentCode = studentCode;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}
