package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.CourseCreateDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.CourseStatus;
import ir.maktab.finalproject.onlinequizapplication.exception.CourseNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.PersonNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.mapper.CourseDtoToCourseMapper;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Person;
import ir.maktab.finalproject.onlinequizapplication.model.Student;
import ir.maktab.finalproject.onlinequizapplication.model.Teacher;
import ir.maktab.finalproject.onlinequizapplication.repository.CourseRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.StudentRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final TeacherRepository teacherRepository;


    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    public Course createCourse(CourseCreateDTO courseCreateDTO) throws ParseException {
        return courseRepository.save(CourseDtoToCourseMapper.mapper(courseCreateDTO));
    }

    public void removeCourse(Long courseID) {
        courseRepository.deleteById(courseID);
    }

    public void enableCourse(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");

        course.get().setCourseStatus(CourseStatus.ENABLE);
        courseRepository.save(course.get());
    }

    public void disableCourse(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");

        course.get().setCourseStatus(CourseStatus.DISABLE);
        courseRepository.save(course.get());
    }

    public void addStudentToCourse(Long courseID, Long studentID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        Optional<Student> student = studentRepository.findById(studentID);
        
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");
        if (!student.isPresent())
            throw new PersonNotFoundException("There isn't any student with this id");

        course.get().addStudent(student.get());
        courseRepository.save(course.get());
    }

    public void removeStudentFromCourse(Long courseID, Long studentID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        Optional<Student> student = studentRepository.findById(studentID);

        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");
        if (!student.isPresent())
            throw new PersonNotFoundException("There isn't any student with this id");

        course.get().removeStudent(student.get());
        courseRepository.save(course.get());
    }

    public void addTeacherToCourse(Long courseID, Long teacherID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);

        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");
        if (!teacher.isPresent())
            throw new PersonNotFoundException("There isn't any teacher with this id");

        course.get().setTeacher(teacher.get());
        courseRepository.save(course.get());
    }

    public void removeTeacherFromCourse(Long courseID, Long teacherID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        Optional<Teacher> teacher = teacherRepository.findById(teacherID);

        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");
        if (!teacher.isPresent())
            throw new PersonNotFoundException("There isn't any teacher with this id");

        course.get().setTeacher(null);
        courseRepository.save(course.get());
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Person> getAllParticipants(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("Course not found!");

        List<Person> participants = new ArrayList<>(course.get().getStudents());
        participants.add(course.get().getTeacher());

        return participants;
    }
}
