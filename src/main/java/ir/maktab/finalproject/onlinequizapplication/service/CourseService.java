package ir.maktab.finalproject.onlinequizapplication.service;

import ir.maktab.finalproject.onlinequizapplication.enumeration.CourseStatus;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Student;
import ir.maktab.finalproject.onlinequizapplication.model.Teacher;
import ir.maktab.finalproject.onlinequizapplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void removeCourse(Course course) {
        courseRepository.delete(course);
    }

    public void removeCourse(Long courseID) {
        courseRepository.deleteById(courseID);
    }

    public Course enableCourse(Long courseID) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().setCourseStatus(CourseStatus.ENABLE);

        return courseRepository.save(course.get());
    }

    public Course disableCourse(Long courseID) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().setCourseStatus(CourseStatus.DISABLE);

        return courseRepository.save(course.get());
    }

    public Course addStudentToCourse(Long courseID, Student student) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().addStudent(student);

        return courseRepository.save(course.get());
    }

    public Course removeStudentFromCourse(Long courseID, Student student) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().removeStudent(student);

        return courseRepository.save(course.get());
    }

    public Course addTeacherToCourse(Long courseID, Teacher teacher) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().setTeacher(teacher);

        return courseRepository.save(course.get());
    }

    public Course removeTeacherFromCourse(Long courseID, Teacher teacher) {
        Optional<Course> course = courseRepository.findById(courseID);
        course.get().setTeacher(null);

        return courseRepository.save(course.get());
    }
}
