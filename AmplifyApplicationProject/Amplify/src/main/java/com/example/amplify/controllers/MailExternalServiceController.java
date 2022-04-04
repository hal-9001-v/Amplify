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
import java.util.Collection;
import java.util.Collections;
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
        model.addAttribute("loggedIn", false);

        User sesuser = new User();
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {

            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("loggedIn", true);

            sesuser = userServices.findByUsername(sessionUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            model.addAttribute("sessionusername", sesuser.getUsername());

            User searchUser = userServices.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<URI> uriList = new ArrayList<URI>();
            List<String> strList = new ArrayList<String>();
            List<Song> songList = new ArrayList<Song>(10);

            List<Song> aux = new ArrayList<Song>();

            List<Song> allDBSongs = songServices.findAll();
            if(!allDBSongs.isEmpty()) {
                for (Song s : allDBSongs) {
                    if(s.getGenre().equals(userServices.getFavouriteGenre(searchUser))){
                        aux.add(s);
                        System.out.println(aux);
                    }
                }
            }
            Collections.shuffle(aux);
            int count;
            if(aux.size() >= 10) count = 10;
            else count = aux.size();
            for (int i = 0; i <count ; i++) {
                songList.add(aux.get(i));
            }

            String uriString = "";
            for (int i = 0; i < songList.size(); i++) {
                String songName = songList.get(i).getTitle();
                uriString = "https://localhost:8443/song/descargar/"+songName;
                uriString = uriString.replace(" ", "%20");
                songName += " " + songServices.findByTitle(songName).get(0).getArtist().getName();
                strList.add(songName);
                URI properURI = new URI(uriString);
                uriList.add(properURI);
            }

            ms.sendRecommendationsMail(searchUser.getUsername(), searchUser.getEmail(), strList, uriList);
        }
        return "main_template";
    }

    @RequestMapping("/redirect/email/estadisticas")
    public String sendStatistics(Model model, @RequestParam(value = "username", required = true) String username) throws URISyntaxException, IOException {

        model.addAttribute("loggedIn", false);

        User sesuser = new User();
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {

            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("loggedIn", true);

            sesuser = userServices.findByUsername(sessionUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            model.addAttribute("sessionusername", sesuser.getUsername());

            User searchUser = userServices.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<URI> uriList = new ArrayList<URI>();
            List<String> strList = new ArrayList<String>();

            strList.add("El total de canciones descargadas de " + searchUser.getUsername() + " es igual a");
            strList.add("El artista favorito de "+ searchUser.getUsername() + " es");
            strList.add("El género favorito de "+ searchUser.getUsername() + " es");

            String s = String.valueOf(searchUser.getTotalSongsDownloaded());
            s = s.replace(" ", "%20");
            uriList.add(new URI(s));
            s = String.valueOf(userServices.getFavouriteArtist(searchUser));
            s = s.replace(" ", "%20");
            uriList.add(new URI(s));
            s = String.valueOf(userServices.getFavouriteGenre(searchUser));
            s = s.replace(" ", "%20");
            uriList.add(new URI(s));

            ms.sendStatisticsMail(searchUser.getUsername(), sesuser.getEmail(), strList, uriList);
        }



        return "main_template";
    }
}


