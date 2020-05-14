package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;
import java.util.*;


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

    @OneToMany(mappedBy = "exam")
    private Set<ExamSheet> examSheets = new HashSet<>();

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
        this.totalGrade = 0.0;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        if (!questions.contains(question)) {
            this.questions.add(question);
            this.totalGrade += question.getGrade();
        }
    }

    public void removeQuestion(Long questionID) {
        Optional<Question> question = questions.stream().filter(q -> q.getId().equals(questionID)).findFirst();
        this.questions.remove(question.get());
        this.totalGrade -= question.get().getGrade();
    }

    public void addExamSheet(ExamSheet sheet) {
        this.examSheets.add(sheet);
    }

    public Set<ExamSheet> getExamSheets() { return examSheets; }
}

