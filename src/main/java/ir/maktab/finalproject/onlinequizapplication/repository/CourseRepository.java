package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByTitle(String title);
    Course findByCourseCode(Long courseCode);
}
