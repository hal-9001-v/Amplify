package com.example.amplify.controllers;

import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.SongRepository;
import com.example.amplify.services.SongServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    SongRepository songRepo;

    @Autowired
    SongServices songServices;

    @RequestMapping({"/", "/inicio", })
    public String main(Model model, HttpSession session) {
        model.addAttribute("loggedIn", false);

        User user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("username", user.getUsername());
        }
        model.addAttribute("songs",songServices.requestRecommendedSongs());

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

        model.addAttribute("songs",songServices.requestRecommendedSongs());

        return "main_template";

    }



    }


