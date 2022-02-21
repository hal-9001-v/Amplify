package com.example.amplify.controllers;


import com.example.amplify.model.Album;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LibraryController {


    @Autowired
    UserServices userServices;



    @RequestMapping("/biblioteca")
    public String defaultLibrary(Model model, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session, loginUser);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
            model.addAttribute("username", loginUser.getUsername());
        }


        return "library_template";

    }

    @RequestMapping("/biblioteca/{username}")
    public String viewUserLibrary(Model model, @PathVariable String username, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session, loginUser);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }
        model.addAttribute("username", username);
        return "library_template";
    }

    @RequestMapping("/biblioteca/{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable String username, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session, loginUser);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());

        }


        model.addAttribute("username", username);
        model.addAttribute("playlists", userServices.findByUsername(username).get(0).getPlaylists());
        return "library_playlist_template";
    }

    @RequestMapping("/biblioteca/{username}/canciones")
    public String showSongs(Model model, @PathVariable("username") String username, HttpSession session){

        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session, loginUser);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        List<Song> favouriteSongs = userServices.findByUsername(username).get(0).getSongs();
        if(!favouriteSongs.isEmpty()) {
            model.addAttribute("songs", favouriteSongs);
        }

        return "library_songs_template";
    }



    @RequestMapping("/biblioteca/{username}/albumes")
    public String viewUserAlbums(Model model, @PathVariable String username, HttpSession session) {

        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session, loginUser);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", loginUser.getUsername());
        List<Album> favouriteAlbums = userServices.findByUsername(username).get(0).getAlbums();
        if(!favouriteAlbums.isEmpty()) {
            model.addAttribute("albums", favouriteAlbums);
        }
        return "library_albums_template";
    }

    @RequestMapping("/biblioteca/{username}/artistas")
    public String viewUserArtists(Model model, @PathVariable String username) {
        model.addAttribute("username", username);
        return "artis_template";
    }
}
