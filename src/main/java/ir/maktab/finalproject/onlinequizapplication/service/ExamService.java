package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.ExamSheetSubmissionStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.CourseNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.ExamNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.*;
import ir.maktab.finalproject.onlinequizapplication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ExamService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final ExamRepository examRepository;
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final ExamSheetRepository examSheetRepository;
    @Autowired
    private final QuestionPropertyService questionPropertyService;
    @Autowired
    private final QuestionItemRepository questionItemRepository;


    public ExamService(CourseRepository courseRepository, ExamRepository examRepository, QuestionRepository questionRepository, ExamSheetRepository examSheetRepository , QuestionPropertyService questionPropertyService, QuestionItemRepository questionItemRepository) {
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.examSheetRepository = examSheetRepository;
        this.questionPropertyService = questionPropertyService;
        this.questionItemRepository = questionItemRepository;
    }


    public Exam getExamByExamID(Long examID) throws ExamNotFoundException {
        Optional<Exam> exam = examRepository.findById(examID);
        if (!exam.isPresent())
            throw new ExamNotFoundException("There isn't any exam with this id!");

        return exam.get();
    }

    public List<Exam> getExamsByCourseID(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id!");

        return course.get().getExams();
    }

    public Exam createExam(CreateExamDTO createExamDTO) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(createExamDTO.getCourseID());
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id!");

        Exam exam = new Exam(
                null,
                createExamDTO.getTitle(),
                createExamDTO.getDescription(),
                Integer.parseInt(createExamDTO.getDuration())
        );

        exam.setCourse(course.get());
        return examRepository.save(exam);
    }

    public List<Exam> getExamsByStudent(Long courseID, Long studentID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id!");

        return course.get()
                .getExams()
                .stream()
                .filter(exam -> examSheetRepository.findByExamAndStudentIDAndSubmissionStatus(exam, studentID, ExamSheetSubmissionStatus.NotSubmitted) != null)
                .collect(Collectors.toList());
    }

    public ExamSheet startExam(StartExamDTO startExamDTO) {
        Exam exam = examRepository.findById(startExamDTO.getExamID()).get();
        ExamSheet examSheet = examSheetRepository.findByExamAndStudentID(exam, startExamDTO.getStudentID());
        if (examSheet != null)
            return examSheet;
        
        examSheet = examSheetRepository.save(new ExamSheet(null, startExamDTO.getStudentID()));

        for(Question q: questionRepository.findAllByExamsOrderById(exam)) {
            QuestionItem questionItem = questionItemRepository.save(new QuestionItem(null, q.getId(), q.getProblemDescription()));
            questionItem.setExamSheet(examSheet);
            examSheet.addQuestionItem(questionItemRepository.save(questionItem));
        }

        examSheet.setExam(exam);

        return examSheetRepository.save(examSheet);
    }

    public void deleteExam(DeleteExamDTO deleteExamDTO) {
        examRepository.deleteById(deleteExamDTO.getExamID());
    }

    public Exam editExam(EditExamDTO editExamDTO) throws ExamNotFoundException {
        Optional<Exam> exam = examRepository.findById(editExamDTO.getExamID());
        if (!exam.isPresent())
            throw new ExamNotFoundException("There isn't any exam with this id!");

        exam.get().setTitle(editExamDTO.getTitle());
        exam.get().setDuration(editExamDTO.getDuration());
        exam.get().setDescription(editExamDTO.getDescription());

        return examRepository.save(exam.get());
    }

    public Exam addQuestion(Long examID, Question question) throws ExamNotFoundException {
        Optional<Exam> exam = examRepository.findById(examID);
        if (!exam.isPresent())
            throw new ExamNotFoundException("There isn't any exam with this id!");

        questionPropertyService.insert(new QuestionProperty(null, question.getId(), examID, question.getGrade()));

        exam.get().addQuestion(question);
        return examRepository.save(exam.get());
    }

    public void removeQuestion(Long examID, Long questionID) throws ExamNotFoundException {
        Optional<Exam> exam = examRepository.findById(examID);
        if (!exam.isPresent())
            throw new ExamNotFoundException("There isn't any exam with this id!");

        exam.get().removeQuestion(questionID);
        examRepository.save(exam.get());
    }

    public void editQuestion(EditQuestionDTO editQuestionDTO) {
        Optional<Question> question = questionRepository.findById(editQuestionDTO.getQuestionID());

        question.get().setTitle(editQuestionDTO.getTitle());
        question.get().setProblemDescription(editQuestionDTO.getProblemDescription());
        questionRepository.save(question.get());

        questionPropertyService.setQuestionGradeInNewExam(editQuestionDTO.getExamID(), editQuestionDTO.getQuestionID(), editQuestionDTO.getGrade());
    }
}

