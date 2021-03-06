package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.model.Exam;
import ir.maktab.finalproject.onlinequizapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByExamsOrderById(Exam exam);
}
