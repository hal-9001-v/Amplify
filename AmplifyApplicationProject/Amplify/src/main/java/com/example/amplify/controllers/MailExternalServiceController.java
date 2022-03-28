package com.example.amplify.controllers;


import com.example.amplify.internalService.MailService;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.services.PlaylistServices;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    SongServices songServices;

    @Autowired
    MailService ms;

    @RequestMapping("/redirect/email/recomendaciones")
    public String sendRecommendations(Model model, @RequestParam(value = "username", required = true) String username) throws URISyntaxException, IOException {

        User user = new User();
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {

            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", user.getUsername());
            user = userServices.findByUsername(sessionUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<URI> uriList = new ArrayList<URI>();
            List<String> strList = new ArrayList<String>();
            List<Song> songList = songServices.requestRecommendedSongs();
            String uriString = "";

            for (int i = 0; i < songList.size(); i++) {
                String songName = songList.get(i).getTitle();
                // uriS = "http://localhost:8443/cancion/" + songName; PROPER LINK WHEN WE ADD SONG REPRODUCTION/DOWNLOAD
                uriString = "http://localhost:8443/album/DB-Rap";
                strList.add(songName);
                URI properURI = new URI(uriString);
                uriList.add(properURI);
            }

            ms.sendRecommendationsMail(user.getUsername(), user.getEmail(), strList, uriList);
        }
        model.addAttribute("loggedIn", false);
        return "main_template";
    }

    @RequestMapping("/redirect/email/estadisticas")
    public String sendStatistics(Model model, @RequestParam(value = "username", required = true) String username) throws URISyntaxException, IOException {
        return "";
    }
}


