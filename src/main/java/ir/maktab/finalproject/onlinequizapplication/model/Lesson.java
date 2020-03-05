package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String topic;

    @OneToMany(mappedBy = "lesson")
    private Set<Course> courses = new HashSet<>();


    public Lesson() {}

    public Lesson(Long id, String name, String topic) {
        this.id = id;
        this.name = name;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
