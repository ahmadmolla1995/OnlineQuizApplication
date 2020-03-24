package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.dto.CourseCreateDTO;
import ir.maktab.finalproject.onlinequizapplication.dto.RemovePersonFromCourseDTO;
import ir.maktab.finalproject.onlinequizapplication.enumeration.CourseStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.CourseNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.PersonNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.mapper.CourseDtoToCourseMapper;
import ir.maktab.finalproject.onlinequizapplication.model.*;
import ir.maktab.finalproject.onlinequizapplication.repository.CourseRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.StudentRepository;
import ir.maktab.finalproject.onlinequizapplication.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


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

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void addStudentToCourse(Long courseID, Long studentID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");

        Optional<Student> student = studentRepository.findById(studentID);
        if (!student.isPresent())
            throw new PersonNotFoundException("There isn't any student with this id");

        course.get().addStudent(student.get());
        courseRepository.save(course.get());
    }

    public void addTeacherToCourse(Long courseID, Long teacherID) throws CourseNotFoundException, PersonNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id");

        Optional<Teacher> teacher = teacherRepository.findById(teacherID);
        if (!teacher.isPresent())
            throw new PersonNotFoundException("There isn't any teacher with this id");

        course.get().setTeacher(teacher.get());
        courseRepository.save(course.get());
    }

    public void removePersonFromCourse(RemovePersonFromCourseDTO personDto) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(personDto.getCourseID());
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id!");

        if (personDto.getRoleType().equals(RoleType.ROLE_STUDENT.toString()))
            course.get().removeStudent(personDto.getPersonID());
        else
            course.get().setTeacher(null);

        courseRepository.save(course.get());
    }

    public List<Person> getAllParticipants(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("Course not found!");

        List<Person> participants = new ArrayList<>();

        Set<Student> students = course.get().getStudents();
        for(Student student: students)
            participants.add(student);

        Teacher teacher = course.get().getTeacher();
        if (teacher != null)
            participants.add(teacher);

        return participants;
    }

    public Set<Question> getArchivedQuestions(Long courseID) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (!course.isPresent())
            throw new CourseNotFoundException("There isn't any course with this id!");

        Set<Question> archivedQuestions = new HashSet<>();
        for(Exam exam: course.get().getExams())
            for (Question question : exam.getQuestions())
                archivedQuestions.add(question);

        return archivedQuestions;
    }
}
