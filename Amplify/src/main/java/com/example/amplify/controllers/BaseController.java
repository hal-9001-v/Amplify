package com.example.amplify.controllers;

import com.example.amplify.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {


    @RequestMapping({"/", "/inicio", })
    public String main(Model model, HttpSession session) {
        model.addAttribute("loggedIn", false);

        User user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("username", user.getUsername());
        }

        return "main_template";

    }

    @RequestMapping({ "inicio/{username}"})
    public String main_user(Model model, HttpSession session, @PathVariable String username) {
        model.addAttribute("loggedIn", false);

        User user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("username", user.getUsername());
        }

        return "main_template";

    }

    }


