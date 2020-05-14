package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.ExamSheetSubmissionStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.*;
import ir.maktab.finalproject.onlinequizapplication.model.*;
import ir.maktab.finalproject.onlinequizapplication.security.AuthenticationService;
import ir.maktab.finalproject.onlinequizapplication.service.*;
import ir.maktab.finalproject.onlinequizapplication.util.ExamSheetCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping(value = "account")
public class AccountController {
    @Autowired
    private final AccountService accountService;
    @Autowired
    private final TeacherService teacherService;
    @Autowired
    private final ExamService examService;
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final CourseService courseService;
    @Autowired
    private final StudentService studentService;
    @Autowired
    private final TeachingAssistantService teachingAssistantService;
    @Autowired
    private final GuestService guestService;
    @Autowired
    private final ExamSheetService examSheetService;


    public AccountController(AccountService accountService, TeacherService teacherService, ExamService examService, QuestionService questionService, CourseService courseService, StudentService studentService, TeachingAssistantService teachingAssistantService, GuestService guestService, ExamSheetService examSheetService) {
        this.accountService = accountService;
        this.teacherService = teacherService;
        this.examService = examService;
        this.questionService = questionService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.teachingAssistantService = teachingAssistantService;
        this.guestService = guestService;
        this.examSheetService = examSheetService;
    }


    @RequestMapping(value = "/signIn")
    private static String showSignInPage() {
        return "signin";
    }

    @RequestMapping(value = "/signUp")
    private static String signUp() {
        return "signup";
    }

    @RequestMapping(value = "/managerPanel")
    private static String showManagerPanel() {
        return "manager_panel";
    }

    @RequestMapping(value = "/teacherPanel")
    private static String showTeacherPanel() {
        return "teacher_panel";
    }

    @RequestMapping(value = "/studentPanel")
    private static String showStudentPanel() {
        return "student_panel";
    }

    @RequestMapping(value = "/teachingAssistantPanel")
    private static String showTeachingAssistantPanel() {
        return "teaching_assistant_panel";
    }

    @RequestMapping(value = "/guestPanel")
    private static String showGuestPanel() {
        return "guest_panel";
    }

    @RequestMapping(value = "/accountNotConfirmedExceptionPage")
    public static String showUnconfirmedAccountPage() {
        return "unconfirmed_account";
    }

    @RequestMapping(value = "/accountRejectedExceptionPage")
    public static String showRejectedAccountPage() {
        return "rejected_account";
    }

    @RequestMapping(value = "/accountNotFoundExceptionPage")
    public static String showAccountNotFoundExceptionPage() {
        return "account_not_found";
    }

    @RequestMapping(value = "/WrongPasswordExceptionPage")
    public static String showWrongPasswordExceptionPage() { return "wrong_password"; }

    @PostMapping(value = "/signUp/signUpCheck")
    public String signUp (@ModelAttribute PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException, RoleNotFoundException {
        accountService.signUp(personRegisterDTO);

        return "redirect:/account/signIn";
    }

    @PostMapping(value = "/signIn/signInCheck")
    public String signIn (@ModelAttribute PersonLoginDTO personLoginDTO, ModelMap model) {
        try {
            PersonSignInCompletionDTO person = accountService.signIn(personLoginDTO);
            model.addAttribute("personLoginDtoCompletion", person);

            RoleType roleType = RoleType.valueOf(person.getRoles().iterator().next().getRoleType());

            if (roleType.equals(RoleType.ROLE_MANAGER))
                return "redirect:/account/managerPanel";
            if (roleType.equals(RoleType.ROLE_TEACHER))
                return "redirect:/account/teacherPanel";
            else if (roleType.equals(RoleType.ROLE_STUDENT))
                return "redirect:/account/studentPanel";
            else if (roleType.equals(RoleType.ROLE_TEACHING_ASSISTANT))
                return "redirect:/account/teachingAssistantPanel";
            else
                return "redirect:/account/guestPanel";

        } catch (AccountNotFoundException e) {
            return "redirect:/account/accountNotFoundExceptionPage";
        } catch (AccountNotConfirmedException e) {
            return "redirect:/account/accountNotConfirmedExceptionPage";
        } catch (AccountRejectedException e) {
            return "redirect:/account/accountRejectedExceptionPage";
        } catch (WrongPasswordException e) {
            return "redirect:/account/WrongPasswordExceptionPage";
        }
    }

    @PostMapping(value = "/managerPanel/filterUsers")
    public String filterUsers(@ModelAttribute AccountSearchDTO accountSearchDTO, ModelMap modelMap) {
        List<Account> accounts = accountService.getAllAccounts(accountSearchDTO);
        modelMap.addAttribute("filteredAccounts", accounts);

        return "user_filter_result";
    }

    @GetMapping (value = "/managerPanel/ListAllAccounts")
    public String listAllAccounts(ModelMap modelMap) {
        List<Account> allAccounts = accountService.getAllAccounts();
        modelMap.addAttribute("all_accounts", allAccounts);

        return "manager_panel_edit_all_accounts";
    }

    @GetMapping (value = "/managerPanel/ListUnconfirmedAccounts")
    public String listUnconfirmedAccounts(ModelMap modelMap) {
        List<Account> unconfirmedAccounts = accountService.getAllAccounts(AccountStatus.WAITING_CONFIRMATION);
        modelMap.addAttribute("unconfirmed_accounts", unconfirmedAccounts);

        return "manager_panel";
    }

    @PostMapping (value = "/managerPanel/confirmAccount")
    public String confirmAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.confirm(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @PostMapping(value = "/managerPanel/rejectAccount")
    public String rejectAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.reject(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @PostMapping(value = "/managerPanel/removeAccount")
    public String removeAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDTO) throws AccountNotFoundException {
        accountService.remove(accountConfirmationDTO.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @PostMapping(value = "/managerPanel/editAccount")
    public String editAccount(@ModelAttribute AccountEditDTO accountEditDTO) throws AccountNotFoundException {
        accountService.editAccount(accountEditDTO);

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses")
    public String viewTeacherCourses(ModelMap modelMap) throws PersonNotFoundException {
        Long teacherID = AuthenticationService.getLoginUser().getPersonID();

        Set<Course> courses = teacherService.getAllCourses(teacherID);
        modelMap.addAttribute("courses", courses);

        return "teacher_panel";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams")
    public String getCourseExams(@ModelAttribute CourseSearchByID course, ModelMap modelMap) throws CourseNotFoundException {
        List<Exam> exams = examService.getExamsByCourseID(course.getCourseID());

        modelMap.addAttribute("exams", exams);
        modelMap.addAttribute("courseID", course.getCourseID());

        return "teacher_panel";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/editExam")
    public String editExam(@ModelAttribute EditExamDTO editExamDTO) throws ExamNotFoundException {
        examService.editExam(editExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/deleteExam")
    public String deleteExam(@ModelAttribute DeleteExamDTO deleteExamDTO) {
        examService.deleteExam(deleteExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/createExam")
    public String createExam(@ModelAttribute CreateExamDTO createExamDTO) throws CourseNotFoundException {
        examService.createExam(createExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/viewQuestions")
    public String viewQuestions(@ModelAttribute ViewExamQuestionsDTO viewExamQuestionsDTO, ModelMap modelMap) throws ExamNotFoundException {
        Set<Question> questions = questionService.getExamQuestions(viewExamQuestionsDTO);

        modelMap.addAttribute("examID", viewExamQuestionsDTO.getExamID());
        modelMap.addAttribute("questions", questions);

        return "exam_questions";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/viewQuestions/deleteQuestion")
    public String removeQuestion(@ModelAttribute DeleteQuestionDTO questionDTO) throws ExamNotFoundException {
        examService.removeQuestion(questionDTO.getExamID(), questionDTO.getQuestionID());

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/viewQuestions/editQuestion")
    public String editQuestion(@ModelAttribute EditQuestionDTO editQuestionDTO) {
        examService.editQuestion(editQuestionDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/createDescriptiveQuestion")
    public String createQuestion(@ModelAttribute AddDescriptiveQuestionDTO questionDTO) throws ExamNotFoundException {
        Question question = questionService.createQuestion(questionDTO);
        examService.addQuestion(questionDTO.getExamID(), question);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @PostMapping(value = "/teacherPanel/viewCourses/viewExams/createMultipleChoiceQuestion")
    public String createQuestion(@ModelAttribute AddMultipleChoiceQuestionDTO questionDTO) throws ExamNotFoundException {
        Question question = questionService.createQuestion(questionDTO);
        examService.addQuestion(questionDTO.getExamID(), question);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/viewArchivedTests")
    public String viewArchivedTests(@ModelAttribute ViewArchivedTestsDTO archivedTestsDto, ModelMap modelMap) throws CourseNotFoundException {
        Set<Question> archivedQuestions = courseService.getArchivedQuestions(archivedTestsDto.getCourseID());

        modelMap.addAttribute("courseID", archivedTestsDto.getCourseID());
        modelMap.addAttribute("examID", archivedTestsDto.getExamID());
        modelMap.addAttribute("archivedQuestions", archivedQuestions);

        return "course_archived_tests";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/addArchivedQuestionToExam")
    public String addArchivedQuestionToExam(@ModelAttribute AddArchivedQuestionToExamDTO questionDto) throws QuestionNotFoundException, ExamNotFoundException {
        Question question = questionService.findByID(questionDto.getQuestionID());
        examService.addQuestion(questionDto.getExamID(), question);

        return "course_archived_tests";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/viewExamSheets")
    public String viewExamSheets(@ModelAttribute ViewExamSheetsDTO viewExamSheetsDTO, ModelMap modelMap) throws ExamNotFoundException {
        Exam exam = examService.getExamByExamID(viewExamSheetsDTO.getExamID());
        modelMap.addAttribute("exam_sheets", exam.getExamSheets());

        return "course_exam_sheet";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/viewExamResults")
    public String viewExamResults(@ModelAttribute ViewExamResultsDTO viewExamResultsDTO, ModelMap modelMap) throws ExamNotFoundException {
        List<ExamSheet> examSheets = examSheetService.findAllByExam_IdOrderById(viewExamResultsDTO.getExamID());

        modelMap.addAttribute("num_of_participants", examSheetService.countByExam_Id(viewExamResultsDTO.getExamID()));

        List<Double> grades = new ArrayList<>();
        for(ExamSheet examSheet: examSheets)
            grades.add(examSheet.getObtainedScore());
        modelMap.addAttribute("exam_results", grades);

        List<String> studentsName = new ArrayList<>();
        for(ExamSheet examSheet: examSheets)
            studentsName.add(studentService.getStudentNameByID(examSheet.getStudentID()));
        modelMap.addAttribute("participants", studentsName);

        return "course_exam_sheet";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/viewExamSheets/viewSheetQuestions")
    public String viewSheetQuestions(@ModelAttribute ViewSheetQuestionsDTO viewSheetQuestionsDTO, ModelMap modelMap) {
        List<QuestionItem> questionItems = examSheetService.getQuestionItemsByExamSheetID(viewSheetQuestionsDTO.getExamSheetID());

        modelMap.addAttribute("sheet_questions", questionItems);
        modelMap.addAttribute("examSheetID", viewSheetQuestionsDTO.getExamSheetID());

        return "course_exam_sheet";
    }

    @GetMapping(value = "/teacherPanel/viewCourses/viewExams/viewExamSheets/viewSheetQuestions/markQuestion")
    public String markQuestion(@ModelAttribute MarkExamSheetDTO markExamSheet) {
        examSheetService.setQuestionGrade(markExamSheet.getExamSheetID(), markExamSheet.getQuestionItemID(), markExamSheet.getGrade());

        return "course_exam_sheet";
    }

    @RequestMapping(value = "/studentPanel/viewCourses")
    public String viewStudentCourses(ModelMap modelMap) throws PersonNotFoundException {
        Long studentID = AuthenticationService.getLoginUser().getPersonID();
        Set<Course> courses = studentService.getStudentCourses(studentID);
        modelMap.addAttribute("courses", courses);

        return "student_panel";
    }

    @RequestMapping(value = "/studentPanel/viewExamResults")
    public String viewStudentExamResults(ModelMap modelMap) {
        Long studentID = AuthenticationService.getLoginUser().getPersonID();
        List<ExamSheet> examSheets = examSheetService.findAllByStudentIDAndSubmissionStatus(studentID, ExamSheetSubmissionStatus.Submitted);
        modelMap.addAttribute("exam_results", examSheets);

        return "student_panel";
    }

    @PostMapping(value = "/studentPanel/viewCourses/viewExams")
    public String viewStudentExams(@ModelAttribute ViewCourseExamsDTO viewCourseExamsDto, ModelMap modelMap) throws CourseNotFoundException {
        List<Exam> exams = examService.getExamsByStudent(viewCourseExamsDto.getCourseID(), viewCourseExamsDto.getStudentID());
        modelMap.addAttribute("courseID", viewCourseExamsDto.getCourseID());
        modelMap.addAttribute("exams", exams);

        return "student_panel";
    }

    @PostMapping(value = "/studentPanel/viewCourses/viewExams/startExam")
    public String startExam (@ModelAttribute StartExamDTO startExamDTO, ModelMap modelMap) {
        ExamSheet examSheet = examService.startExam(startExamDTO);
        ExamSheetCacheManager.setCurrentQuestionNo(0);
        ExamSheetCacheManager.setExamSheet(examSheet);

        modelMap.addAttribute("question", ExamSheetCacheManager.getExamSheet().getQuestionItems().get(0));

        return "exam_page";
    }

    @PostMapping(value = "studentPanel/viewCourses/viewExams/submitExamSheet")
    public String submitExamSheet() {
        examSheetService.submitExamsSheet(ExamSheetCacheManager.getExamSheet());

        return "redirect:/account/studentPanel";
    }

    @PostMapping(value = "/studentPanel/viewCourses/viewExams/changeQuestion")
    public String changeQuestion(@ModelAttribute ChangeExamQuestionDTO changeExamQuestionDTO, ModelMap modelMap) {
        examSheetService.saveCurrentResponse(
                ExamSheetCacheManager.getExamSheet(),
                ExamSheetCacheManager.getCurrentQuestionNo(),
                changeExamQuestionDTO.getResponse()
        );

        ExamSheetCacheManager.changeQuestion(changeExamQuestionDTO);
        modelMap.addAttribute("question", ExamSheetCacheManager.getExamSheet().getQuestionItems().get(ExamSheetCacheManager.getCurrentQuestionNo()));

        return "exam_page";
    }

    @RequestMapping(value = "/guestPanel/viewCourses")
    public String viewUniversityCourses(ModelMap modelMap) {
        List<Course> courses = guestService.getAllCourses();
        modelMap.addAttribute("allCourses", courses);

        return "guest_panel";
    }

    @RequestMapping(value = "/teachingAssistantPanel/viewCourses")
    public String viewAllCoursesByTeachingAssistant(ModelMap modelMap) {
        List<Course> courses = teachingAssistantService.getAllCourses();
        modelMap.addAttribute("allCourses", courses);

        return "teaching_assistant_panel";
    }
}

