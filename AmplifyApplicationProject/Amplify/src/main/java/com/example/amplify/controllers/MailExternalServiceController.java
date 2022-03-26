package com.example.amplify.controllers;


import com.example.amplify.internalService.MailService;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.services.PlaylistServices;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MailExternalServiceController {
    @Autowired
    UserServices userServices;

    @Autowired
    PlaylistServices playlistServices;

    @Autowired
    MailService ms;

    @Autowired
    SongServices songServices;

    @RequestMapping("/redirect/email/recomendaciones")
    public String createPlaylist(Model model,  HttpSession session) throws URISyntaxException, IOException {

        User user;
        user = userServices.checkLogin(session);
        if(user == null) {
            model.addAttribute("loggedIn", false);
            return "main_template";
        }
        else {
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());

            List<URI> uriList = new ArrayList<URI>();
            List<String> strList = new ArrayList<String>();
            List<Song> songList = songServices.requestRecommendedSongs();
            String uriString ="";
            for (int i = 0; i < songList.size(); i++) {
                String songName = songList.get(i).getTitle();
                // uriS = "http://localhost:8080/cancion/" + songName; PROPER LINK WHEN WE ADD SONG REPRODUCTION/DOWNLOAD
                uriString = "http://localhost:8080/album/DB-Rap";
                strList.add(songName);
                URI properURI = new URI(uriString);
                uriList.add(properURI);
            }

            ms.sendRecommendationsMail(user.getUsername(), "dexaxi12@gmail.com", strList, uriList);

            return "main_template";
        }

    }

}
