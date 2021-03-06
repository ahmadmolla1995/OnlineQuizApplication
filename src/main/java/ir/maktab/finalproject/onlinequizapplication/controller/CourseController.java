package ir.maktab.finalproject.onlinequizapplication.controller;

import ir.maktab.finalproject.onlinequizapplication.dto.*;
import ir.maktab.finalproject.onlinequizapplication.exception.CourseNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.exception.PersonNotFoundException;
import ir.maktab.finalproject.onlinequizapplication.model.Course;
import ir.maktab.finalproject.onlinequizapplication.model.Person;
import ir.maktab.finalproject.onlinequizapplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @RequestMapping(value = "/managerPanel", method = RequestMethod.GET)
    public String showMangerCoursePanel() {
        return "manager_courses_panel";
    }

    @RequestMapping(value = "/managerPanel/createCourse", method = RequestMethod.POST)
    public String createCourse(@ModelAttribute CourseCreateDTO courseCreateDTO) throws ParseException {
        courseService.createCourse(courseCreateDTO);

        return "redirect:/course/managerPanel";
    }

    @RequestMapping(value = "/managerPanel/viewCourses", method = RequestMethod.GET)
    public String viewCourses(ModelMap modelMap) {
        List<Course> courses = courseService.getAllCourses();
        modelMap.addAttribute("courses", courses);

        return "manager_courses_panel";
    }

    @RequestMapping(value = "/managerPanel/addStudent", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute AddStudentDTO addStudentDTO) throws CourseNotFoundException, PersonNotFoundException {
        courseService.addStudentToCourse(addStudentDTO.getCourseID(), addStudentDTO.getStudentID());

        return "redirect:/course/managerPanel/viewCourses";
    }

    @RequestMapping(value = "/managerPanel/addTeacher", method = RequestMethod.POST)
    public String addTeacher(@ModelAttribute AddTeacherToCourseDTO addTeacherToCourseDTO) throws CourseNotFoundException, PersonNotFoundException {
        courseService.addTeacherToCourse(addTeacherToCourseDTO.getCourseID(), addTeacherToCourseDTO.getTeacherID());

        return "redirect:/course/managerPanel/viewCourses";
    }

    @RequestMapping(value = "/managerPanel/viewParticipants", method = RequestMethod.POST)
    public String viewCourseParticipants(@ModelAttribute ViewCourseParticipantsDTO courseParticipantsDTO, ModelMap modelMap) throws CourseNotFoundException {
        List<Person> participants = courseService.getAllParticipants(courseParticipantsDTO.getCourseID());

        modelMap.addAttribute("course_participants", participants);
        modelMap.addAttribute("courseID", courseParticipantsDTO.getCourseID());

        return "course_participants";
    }

    @RequestMapping(value = "/managerPanel/viewParticipants/deletePerson", method = RequestMethod.POST)
    public String removePersonFromCourse(@ModelAttribute RemovePersonFromCourseDTO personDto) throws CourseNotFoundException {
        courseService.removePersonFromCourse(personDto);

        return "redirect:/course/managerPanel/viewCourses";
    }
}
