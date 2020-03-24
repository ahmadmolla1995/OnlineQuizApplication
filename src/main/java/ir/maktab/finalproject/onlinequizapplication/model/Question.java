package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String problemDescription;

    @Column(nullable = false)
    private Double grade;

    @ManyToMany(mappedBy = "questions")
    private Set<Exam> exams;


    public Question() {}

    public Question(Long id, String title, String problemDescription, Double grade) {
        this.id = id;
        this.title = title;
        this.problemDescription = problemDescription;
        this.grade = grade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblemDescription() {
        return problemDescription;
    }
    
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Double getGrade() { return grade; }

    public void setGrade(Double grade) { this.grade = grade; }
}
