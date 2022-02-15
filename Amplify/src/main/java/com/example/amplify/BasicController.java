package com.example.amplify;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/hello")
    public String demo(Model model) {
        model.addAttribute("nombre", "Mundo");
        return "demo_template";
    }

}
