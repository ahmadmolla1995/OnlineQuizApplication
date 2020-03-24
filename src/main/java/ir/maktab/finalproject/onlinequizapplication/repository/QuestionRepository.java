package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
