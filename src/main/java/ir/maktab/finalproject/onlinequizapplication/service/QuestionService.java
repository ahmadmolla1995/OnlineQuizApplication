package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.AddDescriptiveQuestionDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.AddMultipleChoiceQuestionDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.ViewExamQuestionsDTO;
import ir.maktab.finalproject.onlinequizapplication.exception.ExamNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.QuestionNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.DescriptiveQuestion;
import ir.maktab.finalproject.onlinequizapplication.model.Exam;
import ir.maktab.finalproject.onlinequizapplication.model.MultipleChoiceQuestion;
import ir.maktab.finalproject.onlinequizapplication.model.Question;
import ir.maktab.finalproject.onlinequizapplication.repository.ExamRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class QuestionService {
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final ExamRepository examRepository;

    public QuestionService(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
    }


    public Set<Question> getExamQuestions(ViewExamQuestionsDTO viewExamQuestionsDTO) throws ExamNotFoundException {
        Optional<Exam> exam = examRepository.findById(viewExamQuestionsDTO.getExamID());
        if (!exam.isPresent())
            throw new ExamNotFoundException("There isn't any exam with this id!");

        return exam.get().getQuestions();
    }

    public Question createQuestion(AddDescriptiveQuestionDTO descriptiveQuestionDTO) {
        return questionRepository.save(
                new DescriptiveQuestion(
                        null,
                        descriptiveQuestionDTO.getTitle(),
                        descriptiveQuestionDTO.getProblemDescription(),
                        descriptiveQuestionDTO.getGrade()
                )
        );
    }

    public Question createQuestion(AddMultipleChoiceQuestionDTO multipleChoiceQuestionDTO) {
        return questionRepository.save(
                new MultipleChoiceQuestion(
                        null,
                        multipleChoiceQuestionDTO.getTitle(),
                        multipleChoiceQuestionDTO.getProblemDescription(),
                        multipleChoiceQuestionDTO.getGrade(),
                        multipleChoiceQuestionDTO.getOptions(),
                        multipleChoiceQuestionDTO.getCorrectResponse()
                )
        );
    }

    public Question find(Long questionID) throws QuestionNotFoundException {
        Optional<Question> question = questionRepository.findById(questionID);
        if (!question.isPresent())
            throw new QuestionNotFoundException("There isn't any question with this id!");

        return question.get();
    }
}
