package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.exception.PersonNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Student;
import ir.maktab.finalproject.onlinequizapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Set<Course> getStudentCourses(Long studentID) throws PersonNotFoundException {
        Optional<Student> student = studentRepository.findById(studentID);
        if (!student.isPresent())
            throw new PersonNotFoundException("There isn't any student with this id!");

        return student.get().getCourses();
    }
}
