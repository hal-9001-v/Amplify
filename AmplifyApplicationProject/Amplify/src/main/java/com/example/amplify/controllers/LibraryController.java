package com.example.amplify.controllers;


import com.example.amplify.model.*;
import com.example.amplify.repositories.AlbumRepository;
import com.example.amplify.services.*;
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

    @Autowired
    AlbumServices albumServices;

    @Autowired
    ArtistServices artistServices;

    @Autowired
    SongServices songServices;

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
    public String addPlaylistToFav(Model model, HttpSession session, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName){
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


        return "main_template";

    }


    @RequestMapping("/bilbioteca/{username}/album/{albumname}/fav")
    public String addAlbumToFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("albumname") String albumname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

            model.addAttribute("username",username);

        Album addableAlbum = albumServices.findByName(albumname).get(0);
        System.out.println(addableAlbum.getName());
        userServices.addAlbum(addableAlbum, loginUser);


        return "main_template";

    }
    @RequestMapping("/bilbioteca/{username}/artista/{artistname}/fav")
    public String addArtistToFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("artistname") String artistname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username",username);

        Artist addableArtist = artistServices.findByName(artistname).get(0);
        System.out.println(addableArtist.getName());
        userServices.addArtist(addableArtist, loginUser);

        return "main_template";

    }

    @RequestMapping("/bilbioteca/{username}/cancion/{songname}/fav")
    public String addSongToFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("songname") String songname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username",username);

        Song addableSong = songServices.findByTitle(songname).get(0);
        System.out.println(addableSong.getTitle());
        userServices.addSong(addableSong, loginUser);

        return "main_template";

    }

    @RequestMapping("/bilbioteca/{username}/playlist/{playlistName}/quitardefav")
    public String removePlaylistFromFav(Model model, HttpSession session, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName){
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

    }

    @RequestMapping("/bilbioteca/{username}/album/{albumname}/quitardefav")
    public String removeAlbumFromFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("albumname") String albumname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        Album removeableAlbum = albumServices.findByName(albumname).get(0);
        userServices.removeAlbum(removeableAlbum, loginUser);


        return "library_playlist_template";

    }

    @RequestMapping("/bilbioteca/{username}/artista/{artistname}/quitardefav")
    public String removeArtistFromFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("artistname") String artistname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        Artist removeableArtist = artistServices.findByName(artistname).get(0);
        userServices.removeArtist(removeableArtist, loginUser);

        return "library_playlist_template";

    }

    @RequestMapping("/bilbioteca/{username}/cancion/{songname}/quitardefav")
    public String removeSongFromFav(Model model, HttpSession session,@PathVariable("username") String username, @PathVariable("songname") String songname){
        model.addAttribute("loggedIn", false);
        User loginUser = userServices.checkLogin(session);
        if (loginUser != null) {

            model.addAttribute("loggedIn", true);
            model.addAttribute("sessionusername", loginUser.getUsername());
        }

        model.addAttribute("username", username);
        Song removeableSong = songServices.findByTitle(songname).get(0);
        userServices.removeSong(removeableSong, loginUser);


        return "library_playlist_template";

    }






}