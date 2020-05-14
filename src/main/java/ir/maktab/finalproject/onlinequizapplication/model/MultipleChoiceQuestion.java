package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;


@Entity
public class MultipleChoiceQuestion extends Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String options;

    @Column(nullable = false)
    private Long correctResponse;


    public MultipleChoiceQuestion() {
        super();
    }

    public MultipleChoiceQuestion(Long id, String title, String problemDescription, Double grade, String options, Long correctResponse) {
        super(id, title, problemDescription, grade);
        this.options = options;
        this.correctResponse = correctResponse;
    }
    

    @Override
    public Long getId() { return id; }

    @Override
    public void setId(Long id) { this.id = id; }

    public Long getCorrectResponse() { return correctResponse; }

    public void setCorrectResponse(Long correctResponse) { this.correctResponse = correctResponse; }

    public String getOptions() { return options; }

    public void setOptions(String options) { this.options = options; }
}
