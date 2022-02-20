package com.example.amplify.controllers;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

public class CreatePlaylistController {

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/crear-playlist")
    public String defaultLibrary(Model model){
        long id = 1;
        Optional<User> defaultUser = userRepo.findById(id);
        if(defaultUser.isPresent()) model.addAttribute("username", defaultUser.get().getUsername());
        else model.addAttribute("username", "AmplifyDefaultUser");
        return "new_playlist_template";
    }

    @RequestMapping("/crear-playlist_{username}")
    public String viewUserLibrary(Model model, @PathVariable String username){

        model.addAttribute("username", username);
        return "new_playlist_template";
    }
}
