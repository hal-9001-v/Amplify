package com.example.amplify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {


    @RequestMapping( {"/", "/inicio"} )
    public String main(Model model) {
        return "main_template";
    }


}
