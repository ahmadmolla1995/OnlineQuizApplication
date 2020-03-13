package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;


@Entity
public class DescriptiveQuestion extends Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String solution;


    public DescriptiveQuestion() {}

    public DescriptiveQuestion(Long id, String solution) {
        this.id = id;
        this.solution = solution;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
