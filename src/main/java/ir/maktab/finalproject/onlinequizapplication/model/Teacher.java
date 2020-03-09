package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Teacher extends Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherCode;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;


    public Teacher() {}

    public Teacher(Long id, String firstName, String lastName, String education, String email, String homeNumber, String cellPhoneNumber, String address, Long teacherCode) {
        super(id, firstName, lastName, education, email, homeNumber, cellPhoneNumber, address);
        this.teacherCode = teacherCode;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(Long teacherCode) {
        this.teacherCode = teacherCode;
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
