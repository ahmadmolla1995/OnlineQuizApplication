package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.model.QuestionProperty;
import ir.maktab.finalproject.onlinequizapplication.repository.QuestionPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionPropertyService {
    @Autowired
    private final QuestionPropertyRepository questionPropertyRepository;

    public QuestionPropertyService(QuestionPropertyRepository questionPropertyRepository) {
        this.questionPropertyRepository = questionPropertyRepository;
    }


    public QuestionProperty insert(QuestionProperty questionProperty) {
        return questionPropertyRepository.save(questionProperty);
    }

    public QuestionProperty setQuestionGradeInNewExam(Long examID, Long questionID, Double grade) {
        QuestionProperty questionProperty = questionPropertyRepository.findByExamIDAndQuestionID(examID, questionID);
        questionProperty.setGrade(grade);

        return questionPropertyRepository.save(questionProperty);
    }
}
