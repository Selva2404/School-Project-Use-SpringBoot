package org.easyschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class logincontroller {

    @RequestMapping(value = "/login",method = {RequestMethod.GET, RequestMethod.POST})
    public String getlogin(@RequestParam(value = "error",required = false) String error,
                           @RequestParam(value = "logout",required = false) String logout,
                           @RequestParam(value = "register", required = false) String register,
                           Model model) {

        String errormessage=null;
        if(error != null){
            errormessage="invalid username or password";
           // model.addAttribute("message","invalid username or password");
        }else if(logout != null){
            errormessage="You have been logged out successfully";
            //model.addAttribute("message","You have been logged out successfully");
        }else if(register != null){
            errormessage="You have been registered successfully";
        }
        model.addAttribute("errormessage",errormessage);
        return "login";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String getlogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        //model.addAttribute("errormessage",errormessage);
        return "redirect:/login";
    }
}
