package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<Exam, Long> {
}
