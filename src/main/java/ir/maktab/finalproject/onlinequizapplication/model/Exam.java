package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Exam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer duration;

    private Double totalGrade;

    @ManyToOne
    private Course course;

    @ManyToMany
    @JoinTable(name = "exams_questions", joinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions = new HashSet<>();


    public Exam() {}

    public Exam(Long id, String title, String description, Integer duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade() {
        this.totalGrade = questions.stream().mapToDouble(Question::getGrade).sum();
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Long questionID) {
        this.questions.removeIf(question -> question.getId().equals(questionID));
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

