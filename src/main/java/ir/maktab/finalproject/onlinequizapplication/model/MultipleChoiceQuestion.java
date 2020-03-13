package ir.maktab.finalproject.onlinequizapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Entity
public class MultipleChoiceQuestion extends Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<String> options = new ArrayList<>();
    private Long response;

    
    public MultipleChoiceQuestion(Long id, List<String> options, Long response) {
        this.id = id;
        this.options = options;
        this.response = response;
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getOptions() {
        return options;
    }

    public Long getResponse() {
        return response;
    }

    public void setResponse(Long response) {
        this.response = response;
    }

    public void addOption(String option) {
        this.options.add(option);
    }

    public void removeOption(String option) {
        this.options.remove(option);
    }
}
