package org.easyschool.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.easyschool.Consents.EasySchoolConsent;
import org.easyschool.Model.Classes;
import org.easyschool.Model.Courses;
import org.easyschool.Model.person;
import org.easyschool.Repository.ClassesRepository;
import org.easyschool.Repository.CoursesRepository;
import org.easyschool.Repository.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    personRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/classes")
    public ModelAndView displayClass(Model model) {

        List<Classes> classes=classesRepository.findAll();
        log.info("Classes list size: "+classes.size());
        log.info("Classes list size: "+classes);
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("allClasses", classes);
        modelAndView.addObject("allClass", new Classes());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public String addNewClass(Classes classes, Model model) {
        classes.setCreatedAt(LocalDateTime.now());
        classes.setCreatedBy(EasySchoolConsent.ADMIN_ROLE);
            classesRepository.save(classes);
        return "redirect:/admin/classes";
    }
    @RequestMapping("/deleteClass")
    public String deleteClass(Model model, int id) {
        classesRepository.deleteById(id);
        return "redirect:/admin/classes";
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {

        String errorMessage;
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<Classes> classes=classesRepository.findById(classId);

        log.error("Classes object: {}", classes);

        modelAndView.addObject("allClass",classes.get());
        modelAndView.addObject("person",new person());
        session.setAttribute("allClass",classes.get());
        if(error != null){
            errorMessage = " fill  the required fields correctly.";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public String addStudent(person person, Model model,HttpSession session){
        person p=personRepository.readByMailid(person.getMailid());
        Classes classes = (Classes) session.getAttribute("allClass");
       if(p == null && !(p.getPerson_id()>0)){
           return "redirect:/admin/displayStudents?classId=" + classes.getClassId()+"&error=true";
       }
       p.setClasses(classes);
       personRepository.save(p);
       classes.getPersons().add(p);
       classesRepository.save(classes);
        return "redirect:/admin/displayStudents?classId=" + classes.getClassId();
    }

    @RequestMapping("/courses")
    public ModelAndView displayCourses(Model model,HttpSession session){
        ModelAndView modelAndView = new ModelAndView("courses_secure");
        modelAndView.addObject("allCourses", coursesRepository.findByOrderByCoursesNameDesc());
        modelAndView.addObject("allCourse", new Courses());
        log.info("Courses object: {}", coursesRepository.findAll());
        session.setAttribute("allCourses",coursesRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public String addNewCourse(Courses courses, Model model){


        coursesRepository.save(courses);
        return "redirect:/admin/courses";
    }

    @RequestMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id, HttpSession session,
                                     @RequestParam(required = false) String error){

        String errorMessage;
        ModelAndView modelAndView = new ModelAndView("course_students");
        Optional<Courses> courses=coursesRepository.findById(id);
        log.error("Course object: {}", courses);
        modelAndView.addObject("allCourse",courses.get());
        modelAndView.addObject("person",new person());
        session.setAttribute("allCourse",courses.get());
        if(error != null){
            errorMessage= " fill  the required fields correctly.";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudentToCourse")
    public String addNewCourseStudent(person person, Model model,HttpSession session){

       // ModelAndView modelandview = new ModelAndView("course_students");
        person p=personRepository.readByMailid(person.getMailid());
        log.error("Person object: {}", p);
        Courses courses=(Courses) session.getAttribute("allCourse");
        log.error("courses object: {}", courses);
        if(p == null && !(p.getPerson_id()>0)){
            return "redirect:/admin/viewStudents?id="+courses.getCoursesId()+"&error=true";
        }
        p.getCourses().add(courses);
        courses.getPersons().add(person);
        personRepository.save(p);
        //coursesRepository.save(courses);
        session.setAttribute("allCourse",courses);
        return "redirect:/admin/viewStudents?id="+courses.getCoursesId();
    }

    @RequestMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(@RequestParam("personId") int personId, HttpSession session){

        Courses courses=(Courses) session.getAttribute("allCourse");
        Optional<person> person=personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person.get());
        personRepository.save(person.get());
        session.setAttribute("allCourse",courses);
        return new ModelAndView("redirect:/admin/viewStudents?id="+courses.getCoursesId());
    }

}
