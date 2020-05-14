package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.exception.PersonNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Teacher;
import ir.maktab.finalproject.onlinequizapplication.repository.QuestionRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;
    @Autowired
    private final QuestionRepository questionRepository;

    public TeacherService(TeacherRepository teacherRepository, QuestionRepository questionRepository) {
        this.teacherRepository = teacherRepository;
        this.questionRepository = questionRepository;
    }


    public Set<Course> getAllCourses(Long teacherID) throws PersonNotFoundException {
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if (!teacher.isPresent())
            throw new PersonNotFoundException("There isn't any teacher with this id!");

        return teacher.get().getCourses();
    }
}
