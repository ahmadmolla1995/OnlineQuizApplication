package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.enumeration.ExamSheetSubmissionStatus;
import ir.maktab.finalproject.onlinequizapplication.model.*;
import ir.maktab.finalproject.onlinequizapplication.repository.ExamSheetRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.QuestionItemRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ExamSheetService {
    @Autowired
    private final ExamSheetRepository examSheetRepository;
    @Autowired
    private final QuestionItemRepository questionItemRepository;
    @Autowired
    private final QuestionRepository questionRepository;


    public ExamSheetService(ExamSheetRepository examSheetRepository, QuestionItemRepository questionItemRepository, QuestionRepository questionRepository) {
        this.examSheetRepository = examSheetRepository;
        this.questionItemRepository = questionItemRepository;
        this.questionRepository = questionRepository;
    }


    public void saveCurrentResponse(ExamSheet examSheet, Integer questionNo, String response) {
        QuestionItem questionItem = examSheet.getQuestionItems().get(questionNo);
        questionItem.setResponse(response);
        questionItemRepository.save(questionItem);

        examSheetRepository.save(examSheet);
    }

    public void submitExamsSheet(ExamSheet examSheet) {
        examSheet.setSubmissionStatus(ExamSheetSubmissionStatus.Submitted);
        examSheetRepository.save(examSheet);
    }

    public List<QuestionItem> getQuestionItemsByExamSheetID(Long examSheetID) {
        return examSheetRepository
                .findById(examSheetID)
                .get()
                .getQuestionItems()
                .stream()
                .filter(q -> questionRepository.findById(q.getQuestionID()).get() instanceof DescriptiveQuestion)
                .collect(Collectors.toList());
    }

    public List<ExamSheet> findAllByStudentIDAndSubmissionStatus(Long studentID, ExamSheetSubmissionStatus status) {
        return examSheetRepository.findAllByStudentIDAndSubmissionStatus(studentID, status);
    }

    public List<ExamSheet> findAllByExam_IdOrderById(Long examID) {
        return examSheetRepository.findAllByExam_IdOrderById(examID);
    }

    public void setQuestionGrade(Long examSheetID, Long questionItemID, Double grade) {
        Optional<ExamSheet> examSheet = examSheetRepository.findById(examSheetID);

        if (examSheet.isPresent()) {
            QuestionItem questionItem = examSheet.get().getQuestionItems().stream().filter(q -> q.getId().equals(questionItemID)).findFirst().get();
            questionItem.setObtainedScore(grade);
            gradeMultipleChoiceQuestions(examSheet.get());
            questionItemRepository.save(questionItem);

            examSheet.get().setObtainedScore();
            examSheetRepository.save(examSheet.get());
        }
    }

    public Long countByExam_Id(Long examID) {
        return examSheetRepository.countByExam_Id(examID);
    }

    private void gradeMultipleChoiceQuestions(ExamSheet examSheet) {
        for(QuestionItem questionItem: examSheet.getQuestionItems()) {
            Question question = questionRepository.findById(questionItem.getQuestionID()).get();
            if (question instanceof MultipleChoiceQuestion) {
                if (questionItem.getResponse().equals(String.valueOf(((MultipleChoiceQuestion) question).getCorrectResponse())))
                    questionItem.setObtainedScore(question.getGrade());
                else
                    questionItem.setObtainedScore(0.0);

                questionItemRepository.save(questionItem);
            }
        }
    }
}
