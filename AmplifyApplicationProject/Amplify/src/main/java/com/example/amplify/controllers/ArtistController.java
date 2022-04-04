package com.example.amplify.controllers;


import com.example.amplify.model.Album;
import com.example.amplify.model.Artist;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.services.ArtistServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    ArtistServices artistServices;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/artista/{artistName}", produces = "application/zip")
    public String viewArtist(Model model, @PathVariable String artistName) {

        Artist artist = artistServices.findByName(artistName).get(0);
        model.addAttribute("artist", artist);

        List<Song> songList = artist.getSongs();
        model.addAttribute("songs", songList);

        List<Album> albumList = artist.getAlbums();
        model.addAttribute("albums", albumList);

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseGet(User::new);

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());
        model.addAttribute("username", user.getUsername());

        return "artist_template";
    }
}
