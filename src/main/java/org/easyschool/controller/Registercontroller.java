package org.easyschool.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.easyschool.Model.person;
import org.easyschool.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("public")
public class Registercontroller {

    @Autowired
    private PersonService personService;


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("person", new person());
        return "register";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("person") person person, Errors errors) {

        boolean isSave=personService.setPerson(person);
       // model.addAttribute("person", new person());
        //log.debug("Registercontroller createUser method called  "+person);
        return "redirect:/login?register=true";
    }

}
