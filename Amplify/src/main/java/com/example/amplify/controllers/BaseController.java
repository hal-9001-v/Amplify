package com.example.amplify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {


    @RequestMapping( {"/", "/inicio"} )
    public String main(Model model) {
        return "main_template";
    }

    @RequestMapping("/inicio_{username}")
    public String mainWithUser(Model model, @PathVariable String username){

        model.addAttribute("username", username);
        return "main_template";

    }

}
