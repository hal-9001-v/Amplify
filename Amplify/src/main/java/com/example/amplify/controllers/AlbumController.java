package com.example.amplify.controllers;


import com.example.amplify.model.Album;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.AlbumRepository;
import com.example.amplify.services.AlbumServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AlbumController {

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    AlbumServices albumServices;

    @Autowired
    UserServices userServices;

    @RequestMapping("/album/{albumName}")
    public String viewAlbum(Model model, @PathVariable String albumName, HttpSession session){

        Album album = albumServices.findByName(albumName).get(0);
        model.addAttribute("album", album);

        List<Song> songList = album.getSongs();
        model.addAttribute("songs", songList);

        User user = new User();
        user = userServices.checkLogin(session);

        if(user == null) model.addAttribute("loggedIn", false);
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());
        }

        return "album_template";
    }


}
