package ir.maktab.finalproject.onlinequizapplication.util;

import ir.maktab.finalproject.onlinequizapplication.dto.ChangeExamQuestionDTO;
import ir.maktab.finalproject.onlinequizapplication.model.ExamSheet;


public class ExamSheetCacheManager {
    private static ExamSheet examSheet;
    private static Integer currentQuestionNo;


    public static ExamSheet getExamSheet() {
        return examSheet;
    }

    public static void setExamSheet(ExamSheet sheet) {
        examSheet = sheet;
    }

    public static Integer getCurrentQuestionNo() {
        return currentQuestionNo;
    }

    public static void setCurrentQuestionNo(Integer questionNo) {
        currentQuestionNo = questionNo;
    }

    public static void changeQuestion(ChangeExamQuestionDTO changeExamQuestionDTO) {
        if (changeExamQuestionDTO.getRequest().equals("previous")) {
            if (currentQuestionNo != 0) {
                currentQuestionNo --;
            }
        }

        else if (changeExamQuestionDTO.getRequest().equals("next")) {
            if (currentQuestionNo != (examSheet.getQuestionItems().size() - 1)) {
                currentQuestionNo ++;
            }
        }
    }
}
