package com.example.amplify.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibraryController {

    @RequestMapping("/biblioteca")
    public String defaultLibrary(Model model){
        return "library_template";
    }
    @RequestMapping("/biblioteca_{username}")
    public String viewUserLibrary(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "library_template";
    }

    @RequestMapping("biblioteca/{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "playlist_template";
    }
    
    @RequestMapping("biblioteca/{username}/songs")
    public String viewUserSongs(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "song_template";
    }

    @RequestMapping("biblioteca/{username}/albums")
    public String viewUserAlbums(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "album_template";
    }

    @RequestMapping("biblioteca/{username}/artists")
    public String viewUserArtists(Model model, @PathVariable String username){
        model.addAttribute("username", username);
        return "artis_template";
    }
}
