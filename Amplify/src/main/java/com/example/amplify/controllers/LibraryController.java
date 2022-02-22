package com.example.amplify.controllers;


import com.example.amplify.model.*;
import com.example.amplify.services.PlaylistServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LibraryController {


    @Autowired
    UserServices userServices;

    @Autowired
    PlaylistServices playlistServices;


    @RequestMapping("/biblioteca")
    public String defaultLibrary(Model model, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session);
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
        loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }
        model.addAttribute("username", username);
        return "library_template";
    }

    @RequestMapping("/biblioteca/{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable("username") String username, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session);
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
        loginUser = userServices.checkLogin(session);
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
        loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        List<Album> favouriteAlbums = userServices.findByUsername(username).get(0).getAlbums();
        if(!favouriteAlbums.isEmpty()) {
            model.addAttribute("albums", favouriteAlbums);
        }
        return "library_albums_template";
    }


    @RequestMapping("/biblioteca/{username}/artistas")
    public String viewUserArtists(Model model, @PathVariable String username, HttpSession session) {
        model.addAttribute("loggedIn", false);
        User loginUser = new User();
        loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        List<Artist> favouriteArtists = userServices.findByUsername(username).get(0).getArtists();
        if(!favouriteArtists.isEmpty()) {
            model.addAttribute("artists", favouriteArtists);
        }
        return "library_artists_template";
    }


    @RequestMapping("/bilbioteca/{username}/playlist/{playlistName}/fav")
    public String addToFav(Model model, HttpSession session, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);

        Playlist addablePlaylist = playlistServices.findByName(playlistName).get(0);
        System.out.println(addablePlaylist.getName());
        userServices.addPlaylist(addablePlaylist, loginUser);


        return "library_playlist_template";

    }
/*
    @RequestMapping("/bilbioteca/{username}/playlist/{playlistName}/quitardefav")
    public String removeFromFav(Model model, HttpSession session, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        Playlist removeablePlaylist = playlistServices.findByName(playlistName).get(0);
        userServices.removePlaylist(removeablePlaylist, loginUser);


        return "library_playlist_template";

    }*/

}
