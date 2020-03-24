package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.enumeration.AccountStatus;
import ir.maktab.finalproject.onlinequizapplication.enumeration.RoleType;
import ir.maktab.finalproject.onlinequizapplication.exception.*;
import ir.maktab.finalproject.onlinequizapplication.model.*;
import ir.maktab.finalproject.onlinequizapplication.security.AuthenticationService;
import ir.maktab.finalproject.onlinequizapplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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


    public AccountController(AccountService accountService, TeacherService teacherService, ExamService examService, QuestionService questionService, CourseService courseService, StudentService studentService, TeachingAssistantService teachingAssistantService , GuestService guestService) {
        this.accountService = accountService;
        this.teacherService = teacherService;
        this.examService = examService;
        this.questionService = questionService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.teachingAssistantService = teachingAssistantService;
        this.guestService = guestService;
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

    @RequestMapping (value = "/signUp/signUpCheck", method = RequestMethod.POST)
    public String signUp (@ModelAttribute PersonRegisterDTO personRegisterDTO) throws AccountAlreadyExistsException, RoleNotFoundException {
        accountService.signUp(personRegisterDTO);

        return "redirect:/account/signIn";
    }

    @RequestMapping (value = "/signIn/signInCheck", method = RequestMethod.POST)
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


    @RequestMapping(value = "/managerPanel/filterUsers", method = RequestMethod.POST)
    public String filterUsers(@ModelAttribute AccountSearchDTO accountSearchDTO, ModelMap modelMap) {
        List<Account> accounts = accountService.getAllAccounts(accountSearchDTO);
        modelMap.addAttribute("filteredAccounts", accounts);

        return "user_filter_result";
    }

    @RequestMapping (value = "/managerPanel/ListUnconfirmedAccounts", method = RequestMethod.GET)
    public String listUnconfirmedAccounts(ModelMap modelMap) {
        List<Account> unconfirmedAccounts = accountService.getAllAccounts(AccountStatus.WAITING_CONFIRMATION);
        modelMap.addAttribute("unconfirmed_accounts", unconfirmedAccounts);

        return "manager_panel";
    }

    @RequestMapping (value = "/managerPanel/confirmAccount", method = RequestMethod.POST)
    public String confirmAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.confirm(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping (value = "/managerPanel/rejectAccount", method = RequestMethod.POST)
    public String rejectAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDto) throws AccountNotFoundException {
        accountService.reject(accountConfirmationDto.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping(value = "/managerPanel/removeAccount", method = RequestMethod.POST)
    public String removeAccount(@ModelAttribute AccountConfirmationDTO accountConfirmationDTO) throws AccountNotFoundException {
        accountService.remove(accountConfirmationDTO.getAccountID());

        return "redirect:/account/managerPanel/ListUnconfirmedAccounts";
    }

    @RequestMapping (value = "/managerPanel/editAccount", method = RequestMethod.POST)
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

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams", method = RequestMethod.POST)
    public String getCourseExams(@ModelAttribute CourseSearchByID course, ModelMap modelMap) throws CourseNotFoundException {
        List<Exam> exams = examService.getExamsByCourseID(course.getCourseID());

        modelMap.addAttribute("exams", exams);
        modelMap.addAttribute("courseID", course.getCourseID());

        return "teacher_panel";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/editExam", method = RequestMethod.POST)
    public String editExam(@ModelAttribute EditExamDTO editExamDTO) throws ExamNotFoundException {
        examService.editExam(editExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/deleteExam", method = RequestMethod.POST)
    public String deleteExam(@ModelAttribute DeleteExamDTO deleteExamDTO) throws ExamNotFoundException {
        examService.deleteExam(deleteExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/createExam", method = RequestMethod.POST)
    public String createExam(@ModelAttribute CreateExamDTO createExamDTO) throws CourseNotFoundException {
        examService.createExam(createExamDTO);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/viewQuestions", method = RequestMethod.POST)
    public String viewQuestions(@ModelAttribute ViewExamQuestionsDTO viewExamQuestionsDTO, ModelMap modelMap) throws ExamNotFoundException {
        Set<Question> questions = questionService.getExamQuestions(viewExamQuestionsDTO);

        modelMap.addAttribute("examID", viewExamQuestionsDTO.getExamID());
        modelMap.addAttribute("questions", questions);

        return "exam_questions";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/viewQuestions/deleteQuestion", method = RequestMethod.POST)
    public String removeQuestion(@ModelAttribute DeleteQuestionDTO questionDTO) throws ExamNotFoundException {
        examService.removeQuestion(questionDTO.getExamID(), questionDTO.getQuestionID());

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/createDescriptiveQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute AddDescriptiveQuestionDTO questionDTO) throws ExamNotFoundException {
        Question question = questionService.createQuestion(questionDTO);
        examService.addQuestion(questionDTO.getExamID(), question);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/createMultipleChoiceQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute AddMultipleChoiceQuestionDTO questionDTO) throws ExamNotFoundException {
        Question question = questionService.createQuestion(questionDTO);
        examService.addQuestion(questionDTO.getExamID(), question);

        return "redirect:/account/teacherPanel/viewCourses";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/viewArchivedTests", method = RequestMethod.GET)
    public String viewArchivedTests(@ModelAttribute ViewArchivedTestsDTO archivedTestsDto, ModelMap modelMap) throws CourseNotFoundException {
        Set<Question> archivedQuestions = courseService.getArchivedQuestions(archivedTestsDto.getCourseID());

        modelMap.addAttribute("courseID", archivedTestsDto.getCourseID());
        modelMap.addAttribute("examID", archivedTestsDto.getExamID());
        modelMap.addAttribute("archivedQuestions", archivedQuestions);

        return "course_archived_tests";
    }

    @RequestMapping(value = "/teacherPanel/viewCourses/viewExams/addArchivedQuestionToExam", method = RequestMethod.GET)
    public String addArchivedQuestionToExam(@ModelAttribute AddArchivedQuestionToExamDTO questionDto) throws QuestionNotFoundException, ExamNotFoundException {
        examService.addQuestion(questionDto.getExamID(), questionService.find(questionDto.getQuestionID()));

        return "course_archived_tests";
    }

    @RequestMapping(value = "/studentPanel/viewCourses")
    public String viewStudentCourses(ModelMap modelMap) throws PersonNotFoundException {
        Long studentID = AuthenticationService.getLoginUser().getPersonID();

        Set<Course> courses = studentService.getStudentCourses(studentID);
        modelMap.addAttribute("courses", courses);

        return "student_panel";
    }

    @RequestMapping(value = "/studentPanel/viewCourses/viewExams", method = RequestMethod.POST)
    public String getStudentExams(@ModelAttribute CourseSearchByID course, ModelMap modelMap) throws CourseNotFoundException {
        List<Exam> exams = examService.getExamsByCourseID(course.getCourseID());

        modelMap.addAttribute("courseID", course.getCourseID());
        modelMap.addAttribute("exams", exams);

        return "student_panel";
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
