package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.model.Question;
import ir.maktab.finalproject.onlinequizapplication.model.QuestionProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionPropertyRepository extends JpaRepository<QuestionProperty, Long> {
    QuestionProperty findByExamIDAndQuestionID(Long examID, Long questionID);
}
