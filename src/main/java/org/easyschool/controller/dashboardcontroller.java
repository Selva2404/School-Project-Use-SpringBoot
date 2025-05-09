package org.easyschool.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class dashboardcontroller {


    @RequestMapping("/dashboard")
    public String displayUser(Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        model.addAttribute("usernames",authentication.getName());
        model.addAttribute("roles",authentication.getAuthorities().toString());

        return "dashboard.html";
    }

}
