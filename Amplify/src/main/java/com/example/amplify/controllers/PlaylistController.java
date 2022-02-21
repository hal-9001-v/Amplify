package com.example.amplify.controllers;

import com.example.amplify.model.Playlist;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.services.PlaylistServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepo;

    @Autowired
    PlaylistServices playlistServices;

    @RequestMapping("/crear-playlist")
    public String sendToLogin(Model model) {
        return "login_template";
    }

    @RequestMapping("/crear-playlist/{username}")
    public String viewCreatePlaylistWindow(Model model, @PathVariable String username) {
        model.addAttribute("loggedIn", true);
        model.addAttribute("username", username);
        return "new_playlist_template";
    }

    @RequestMapping("/playlist/{username}/addPlaylist")
    public String createPlaylist(Model model, @PathVariable String username, @RequestParam String playlistName){
        model.addAttribute("loggedIn", true);
        model.addAttribute("username", username);
        Playlist newPLaylist = new Playlist(playlistName);
        model.addAttribute("playlist", newPLaylist);
        playlistRepo.save(newPLaylist);
        return "playlist_template";
    }


    @RequestMapping("/playlist/{playlistName}" )
    public String viewPlaylistNoUser(Model model, @PathVariable("playlistName") String playlistName){
        model.addAttribute("loggedIn", false);
        Playlist requestedPlaylist = playlistServices.findByName(playlistName).get(0);
        model.addAttribute("playlist", requestedPlaylist);


        return "playlist_template";
    }

    @RequestMapping("/playlist/{username}/{playlistName}" )
    public String viewPlaylistUser(Model model,@PathVariable("username") String username, @PathVariable("playlistName") String playlistName){
        model.addAttribute("loggedIn", true);
        model.addAttribute("username", username);
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

}
