package ir.maktab.finalproject.onlinequizapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class DisplayController {
    @RequestMapping(value = "/account/signIn")
    private static String showSignInPage() {
        return "signin";
    }

    @RequestMapping(value = "/account/signUp")
    private static String signUp() {
        return "signup";
    }

    @RequestMapping(value = "/account/managerPanel")
    private static String showManagerPanel() {
        return "manager_panel";
    }

    @RequestMapping(value = "/account/studentPanel")
    private static String showStudentPanel() {
        return "student_panel";
    }

    @RequestMapping(value = "/account/teacherPanel")
    private static String showTeacherPanel() {
        return "teacher_panel";
    }
}
