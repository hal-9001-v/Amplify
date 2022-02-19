package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Controller
public class DemoApplication {
    Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



    @GetMapping("/index")
    public String sayHello(Model model) {

        model.addAttribute("article", "So very true but will give");

        logger.info("Log!");

        return "index";
    }

    @GetMapping("/article")
    public String getUsers(Model model) {


        model.addAttribute("matar", true);

        return "article";

    }

}

