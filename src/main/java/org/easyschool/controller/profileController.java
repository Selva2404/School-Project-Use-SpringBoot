package org.easyschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.Profile;
import org.easyschool.Model.person;
import org.easyschool.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class profileController {

    @Autowired
    ProfileService profileService;

    @RequestMapping("/viewProfile")
    public ModelAndView viewProfile(HttpSession httpSession){
        Profile profile = new Profile();
        person person=(person)httpSession.getAttribute("defaultDisplay");
        if(person != null && person.getPerson_id()>0){
            profile.setName(person.getName());
            profile.setMailid(person.getMailid());
            profile.setMobileNum(person.getMobileNum());
            if(person.getAddress() !=null && person.getAddress().getAddressid()>0)
            {
                profile.setAddress1(person.getAddress().getAddress1());
                profile.setAddress2(person.getAddress().getAddress2());
                profile.setCity(person.getAddress().getCity());
                profile.setState(person.getAddress().getState());
                profile.setZipcode(person.getAddress().getZipcode());
            }
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile",profile);
        return modelAndView;
    }

    @PostMapping ("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile,Errors error,HttpSession session){

        if (error.hasErrors()) {
            return "profile";
       }
        profileService.updateuserdetails(profile, session);
        return "redirect:/viewProfile";

    }
}
