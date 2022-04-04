package com.example.amplify.controllers;

import com.example.amplify.repositories.SongRepository;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @Autowired
    SongRepository songRepo;

    @Autowired
    SongServices songServices;
    @Autowired
    UserServices userServices;

    @RequestMapping({"/", "/inicio",})
    public String main(Model model) {

        String username = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails) sessionUser).getUsername();
            logged = true;
        }

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", username);

        model.addAttribute("songs", songServices.requestRecommendedSongs());
        return "main_template";
    }

    @RequestMapping({"inicio/{username}"})
    public String main_user(Model model, HttpSession session, @PathVariable String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails) sessionUser).getUsername();
            logged = true;
        }

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", username);
        model.addAttribute("songs", songServices.requestRecommendedSongs());

        return "main_template";

    }

    @RequestMapping({"/statisticsMail"})
    public String sendStatisticMails(Model model) {
        return "mail_statistics_template";
    }

    @RequestMapping({"/recommendationsMail"})
    public String sendRecommendationsMails(Model model) {
        return "mail_recommendations_template";
    }
}


