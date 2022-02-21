package com.example.amplify.controllers;

import com.example.amplify.model.Playlist;
import com.example.amplify.model.User;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.services.PlaylistServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepo;

    @Autowired
    PlaylistServices playlistServices;

    @Autowired
    UserServices userServices;

    @RequestMapping("/crear-playlist")
    public String sendToLogin(Model model, HttpSession session) {
        User user = new User();
        user = userServices.checkLogin(session, user);
        if(user == null) {
            model.addAttribute("loggedIn", false);
            return "main_template";
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());
        }
        return "new_playlist_template";

    }

    @RequestMapping("/crear-playlist/{username}")
    public String viewCreatePlaylistWindow(Model model, @PathVariable String username, HttpSession session) {
        User user = new User();
        user = userServices.checkLogin(session, user);
        if(user == null) model.addAttribute("loggedIn", false);

        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());
        }
        return "new_playlist_template";
    }

    @RequestMapping("/playlist/{username}/addPlaylist")
    public String createPlaylist(Model model, @PathVariable String username, @RequestParam String playlistName, HttpSession session){
        User user = new User();
        user = userServices.checkLogin(session, user);
        if(user == null) {
            model.addAttribute("loggedIn", false);
            return "main_template";
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

            //Create playlist
            Playlist newPLaylist = new Playlist();
            newPLaylist = playlistServices.addPlaylist(playlistName, user);
            //userServices.AddPlaylist(newPLaylist, user);

            model.addAttribute("playlist", newPLaylist);

            return "playlist_template";
        }

    }


    @RequestMapping("/playlist/{playlistName}" )
    public String viewPlaylistNoUser(Model model, @PathVariable("playlistName") String playlistName, HttpSession session){
        User user = new User();
        user = userServices.checkLogin(session, user);
        if(user == null) {
            model.addAttribute("loggedIn", false);
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

        }
        Playlist requestedPlaylist = playlistServices.findByName(playlistName).get(0);
        model.addAttribute("playlist", requestedPlaylist);
        return "playlist_template";


    }


    @RequestMapping("/playlist/{playlistName}/deletePlaylist")
    public String deletePlaylist(Model model, @PathVariable String playlistName){
        model.addAttribute("loggedIn", false);
        List <Playlist> list = playlistServices.findByName(playlistName);
        if(list.size() > 0) {
            Playlist deletablePlaylist = list.get(0);
            playlistRepo.delete(deletablePlaylist);
        }


        return "main_template";
    }

/*
    @RequestMapping("/playlist/{playlistName}/añadiraFav")
    public String addToFav(Model model, @PathVariable String playlistName){

        return "main_template";

    }

    @RequestMapping("/playlist/{playlistName}/añadiraFav")
    public String removeFromFav(Model model, @PathVariable String playlistName){


        return "main_template";

    }*/

}
