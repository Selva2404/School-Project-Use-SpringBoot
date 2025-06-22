package org.easyschool.controller;



import jakarta.servlet.http.HttpSession;
import org.easyschool.Model.person;
import org.easyschool.Repository.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class dashboardcontroller {

    @Autowired
    personRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayUser(Model model, Authentication authentication, HttpSession
                              httpSession) {
        if (authentication == null) {
            return "redirect:/login";
        }
        person person = personRepository.readByMailid(authentication.getName());
        model.addAttribute("usernames", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if (person.getClasses() != null && person.getClasses().getClassName() != null) {
            model.addAttribute("className", person.getClasses().getClassName().toString());
        }
        httpSession.setAttribute("defaultDisplay", person);
        return "dashboard";
    }

}
