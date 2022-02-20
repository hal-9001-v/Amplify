package com.example.amplify.controllers;


import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class LibraryController {


    @Autowired
    UserServices userServices;

    @RequestMapping("/biblioteca")
    public String defaultLibrary(Model model) {

        model.addAttribute("loggedIn", false);

        return "library_template";

    }

    @RequestMapping("/biblioteca_{username}")
    public String viewUserLibrary(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        model.addAttribute("loggedIn", true);
        return "library_template";
    }

    @RequestMapping("/biblioteca_{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        return "playlist_template";
    }

    @RequestMapping("/biblioteca_{username}/songs")
    public String viewUserSongs(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        return "song_template";
    }

    @RequestMapping("/biblioteca_{username}/albums")
    public String viewUserAlbums(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        return "album_template";
    }

    @RequestMapping("/biblioteca_{username}/artists")
    public String viewUserArtists(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        return "artis_template";
    }
}
