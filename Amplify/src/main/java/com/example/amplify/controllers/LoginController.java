package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import com.example.amplify.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static String UserSessionKey = "userInfo";

    private String nameFormKey;
    private String passwordFormKey;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserServices userServices;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 1; i++) {
            // userRepo.save(new User("AmplifyDefaultUserFromDB", "12345"));
        }
    }

    @GetMapping("/login")
    public String logInPage(Model model, HttpSession session) {
        return "login_template";
    }

    /*
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, params = "login")
    public String logInUser(Model model, HttpSession session, @RequestParam(value = "sessionusername", required = true) String sessionusername, @RequestParam(value = "password", required = true) String password) {

        List<User> userList = userServices.findByUsername(sessionusername);

        if (userList.isEmpty() == false) {

            if (userList.get(0).isPassword(password)) {

                session.setAttribute(UserSessionKey, userList.get(0));

                logger.info("Logged successfully!");
                return "/inicio";
            }
            logger.info("Incorrect Password for user!");
        }

        logger.info("Failed to log!");

        return "login_template";
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST, params = "signIn")
    public String SignInUser(Model model, HttpSession session, @RequestParam(value = "sessionusername", required = true) String sessionusername, @RequestParam(value = "password", required = true) String password) {

        List<User> userList = userServices.findByUsername(sessionusername);

        if (userList.isEmpty()) {

            User user = new User(sessionusername, password);
            session.setAttribute(UserSessionKey, user);

            userRepo.save(user);

            logger.info("Signed successfully!");
            return "/inicio";
        }

        logger.info("Failed to SignIn!");

        return "login_template";
    }
    */
}
