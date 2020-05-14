package ir.maktab.finalproject.onlinequizapplication.repository;

import ir.maktab.finalproject.onlinequizapplication.enumeration.ExamSheetSubmissionStatus;
import ir.maktab.finalproject.onlinequizapplication.model.Exam;
import ir.maktab.finalproject.onlinequizapplication.model.ExamSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExamSheetRepository extends JpaRepository<ExamSheet, Long> {
    Long countByExam_Id(Long examID);
    ExamSheet findByExamAndStudentID (Exam exam, Long studentID);
    ExamSheet findByExamAndStudentIDAndSubmissionStatus(Exam exam, Long studentID, ExamSheetSubmissionStatus sheetSubmissionStatus);
    List<ExamSheet> findAllByStudentIDAndSubmissionStatus(Long studentID, ExamSheetSubmissionStatus status);
    List<ExamSheet> findAllByExam_IdOrderById(Long examID);
}
