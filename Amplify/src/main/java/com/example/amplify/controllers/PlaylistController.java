package com.example.amplify.controllers;

import com.example.amplify.model.Playlist;
import com.example.amplify.model.User;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.repositories.UserRepository;
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

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/crear-playlist")
    public String sendToLogin(Model model, HttpSession session) {
        User user = new User();
        user = userServices.checkLogin(session);
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
        user = userServices.checkLogin(session);
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
        user = userServices.checkLogin(session);
        if(user == null) {
            model.addAttribute("loggedIn", false);
            return "main_template";
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

            //Create playlist
            Playlist newPLaylist = new Playlist();
            newPLaylist = playlistServices.createPlaylist(playlistName, user);
            userServices.addPlaylist(newPLaylist, user);

            model.addAttribute("playlist", newPLaylist);

            return "playlist_template";
        }

    }


    @RequestMapping("/playlist/{playlistName}" )
    public String viewPlaylistNoUser(Model model, @PathVariable("playlistName") String playlistName, HttpSession session){
        User user =  userServices.checkLogin(session);
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

    
    @RequestMapping("/playlist/{playlistName}/eliminarPlaylist")
    public String deletePlaylist(Model model, @PathVariable String playlistName, HttpSession session){
        User user =  userServices.checkLogin(session);
        if(user == null) {
            model.addAttribute("loggedIn", false);
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

        }

        List <Playlist> list = playlistServices.findByName(playlistName);
        if(list.size() > 0) {
            Playlist deletablePlaylist = list.get(0);
            playlistRepo.delete(playlistServices.findByName(deletablePlaylist.getName()).get(0));
        }

        return "main_template";
    }

    @RequestMapping("/playlist/anadir/{songtitle}")
    public String addToPlaylist(Model model, HttpSession session, @PathVariable String songtitle){

        User user =  userServices.checkLogin(session);
        if(user == null) {
            model.addAttribute("loggedIn", false);
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

        }
        

        return "display_owned_playlists_template";
    }


}
