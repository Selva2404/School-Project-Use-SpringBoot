package org.easyschool.controller;

import jakarta.servlet.http.HttpSession;
import org.easyschool.Model.person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student")
public class StudentController {

    @RequestMapping("/courses")
    public ModelAndView viewStudentCourses(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("courses_enrolled");
        person person=(person) session.getAttribute("defaultDisplay");
        modelAndView.addObject("person", person);
        return modelAndView;
    }
}
