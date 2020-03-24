package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.CreateExamDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.DeleteExamDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.EditExamDTO;
import ir.maktab.finalproject.onlinequizapplication.exception.CourseNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.ExamNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Exam;
import ir.maktab.finalproject.onlinequizapplication.model.Question;
import ir.maktab.finalproject.onlinequizapplication.repository.CourseRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExamService {
    @Autowired
    private final ExamRepository examRepository;
    @Autowired
    private final CourseRepository courseRepository;

    public ExamService(ExamRepository examRepository, CourseRepository courseRepository) {
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
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

    public void deleteExam(DeleteExamDTO deleteExamDTO) throws ExamNotFoundException {
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
}

