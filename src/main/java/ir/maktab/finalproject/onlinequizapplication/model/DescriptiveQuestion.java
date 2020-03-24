package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.*;


@Entity
public class DescriptiveQuestion extends Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public DescriptiveQuestion() {
        super();
    }

    public DescriptiveQuestion(Long id, String title, String problemDescription, Double grade) {
        super(id, title, problemDescription, grade);
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
