package org.easyschool.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.easyschool.Model.Contect;
import org.easyschool.service.contectservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
public class contectcontroller {
   // Logger logger = Logger.getLogger(contectcontroller.class.getName());
   @RequestMapping(value = "/contact")
    public String getpage(Model model){
       model.addAttribute("Contact",new Contect());
        return "contact";
    }

    contectservice contectservice;

    @Autowired
    public void contectservice(contectservice contectservice) {
        this.contectservice = contectservice;
    }


    @PostMapping(value = "/saveMsg")
    public String collectdata(@Valid @ModelAttribute("Contact") Contect contect, Errors errors) {
        if(errors.hasErrors()){
            //log.error(errors.getAllErrors().toString());
            return "contact";
        }
        contectservice.getdata(contect);
        return "redirect:/contact";
    }

    @RequestMapping( "/displayMessages" )
    public ModelAndView getContactdDtails(){
        List<Contect> contactmsg=contectservice.getData();
        ModelAndView view = new ModelAndView("messages.html");
        view.addObject("contactmsg",contactmsg);
        return view;

    }

    @GetMapping("/closeMsg")
    public String closeMsg(@RequestParam() int id, Authentication auth){
        log.debug("closeMsg controller id"+id);
        contectservice.updateMsgClose(id, auth.getName() );
        return "redirect:/displayMessages";
    }
}
