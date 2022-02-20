package com.example.amplify.controllers;


import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class LibraryController {

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/biblioteca")
    public String defaultLibrary(Model model){
        long id = 1;
        Optional<User> defaultUser = userRepo.findById(id);
        if(defaultUser.isPresent()) model.addAttribute("username", defaultUser.get().getUsername());
        else model.addAttribute("username", "AmplifyDefaultUser");
        return "library_template";
    }
    @RequestMapping("/biblioteca_{username}")
    public String viewUserLibrary(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "library_template";
    }

    @RequestMapping("/biblioteca_{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "playlist_template";
    }

    @RequestMapping("/biblioteca_{username}/songs")
    public String viewUserSongs(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "song_template";
    }

    @RequestMapping("/biblioteca_{username}/albums")
    public String viewUserAlbums(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "album_template";
    }

    @RequestMapping("/biblioteca_{username}/artists")
    public String viewUserArtists(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "artis_template";
    }
}
