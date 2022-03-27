package com.example.amplify.controllers;


import com.example.amplify.model.*;
import com.example.amplify.repositories.AlbumRepository;
import com.example.amplify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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
    public String defaultLibrary(Model model) {

        String sessionUsername;
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            model.addAttribute("username", sessionUsername);
            logged = true;
        }

        model.addAttribute("loggedIn", logged);
        return "library_template";
    }

    @RequestMapping("/biblioteca/{username}")
    public String viewUserLibrary(Model model, @PathVariable String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);
        return "library_template";
    }

    @RequestMapping("/biblioteca/{username}/playlists")
    public String viewUserPlaylists(Model model, @PathVariable("username") String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("playlists", user.getPlaylists());

        return "library_playlist_template";
    }

    @RequestMapping("/biblioteca/{username}/canciones")
    public String showSongs(Model model, @PathVariable("username") String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Song> favouriteSongs = user.getSongs();

        if (!favouriteSongs.isEmpty()) {
            model.addAttribute("songs", favouriteSongs);
        }

        return "library_songs_template";
    }

    @RequestMapping("/biblioteca/{username}/albumes")
    public String viewUserAlbums(Model model, @PathVariable String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Album> favouriteAlbums = user.getAlbums();
        if (!favouriteAlbums.isEmpty()) {
            model.addAttribute("albums", favouriteAlbums);
        }

        return "library_albums_template";
    }

    @RequestMapping("/biblioteca/{username}/artistas")
    public String viewUserArtists(Model model, @PathVariable String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Artist> favouriteArtists = user.getArtists();
        if (!favouriteArtists.isEmpty()) {
            model.addAttribute("artists", favouriteArtists);
        }
        return "library_artists_template";
    }


    @RequestMapping("/bilbioteca/{username}/playlist/{playlistName}/fav")
    public String addPlaylistToFav(Model model, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Playlist addablePlaylist = playlistServices.findByName(playlistName).get(0);
        System.out.println(addablePlaylist.getName());
        userServices.addPlaylist(addablePlaylist, user);

        return "main_template";
    }


    @RequestMapping("/bilbioteca/{username}/album/{albumname}/fav")
    public String addAlbumToFav(Model model, @PathVariable("username") String username, @PathVariable("albumname") String albumname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Album addableAlbum = albumServices.findByName(albumname).get(0);
        System.out.println(addableAlbum.getName());
        userServices.addAlbum(addableAlbum, user);

        return "main_template";
    }

    @RequestMapping("/bilbioteca/{username}/artista/{artistname}/fav")
    public String addArtistToFav(Model model, @PathVariable("username") String username, @PathVariable("artistname") String artistname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Artist addableArtist = artistServices.findByName(artistname).get(0);
        System.out.println(addableArtist.getName());
        userServices.addArtist(addableArtist, user);

        return "main_template";
    }

    @RequestMapping("/bilbioteca/{username}/cancion/{songname}/fav")
    public String addSongToFav(Model model, @PathVariable("username") String username, @PathVariable("songname") String songname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Song addableSong = songServices.findByTitle(songname).get(0);
        System.out.println(addableSong.getTitle());
        userServices.addSong(addableSong, user);

        return "main_template";
    }

    @RequestMapping("/bilbioteca/{username}/playlist/{playlistName}/quitardefav")
    public String removePlaylistFromFav(Model model, @PathVariable("username") String username, @PathVariable("playlistName") String playlistName) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Playlist removeablePlaylist = playlistServices.findByName(playlistName).get(0);
        userServices.removePlaylist(removeablePlaylist, user);

        return "library_playlist_template";
    }

    @RequestMapping("/bilbioteca/{username}/album/{albumname}/quitardefav")
    public String removeAlbumFromFav(Model model, @PathVariable("username") String username, @PathVariable("albumname") String albumname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Album removeableAlbum = albumServices.findByName(albumname).get(0);
        userServices.removeAlbum(removeableAlbum, user);

        return "library_playlist_template";
    }

    @RequestMapping("/bilbioteca/{username}/artista/{artistname}/quitardefav")
    public String removeArtistFromFav(Model model, @PathVariable("username") String username, @PathVariable("artistname") String artistname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Artist removeableArtist = artistServices.findByName(artistname).get(0);
        userServices.removeArtist(removeableArtist, user);

        return "library_playlist_template";

    }

    @RequestMapping("/bilbioteca/{username}/cancion/{songname}/quitardefav")
    public String removeSongFromFav(Model model, HttpSession session, @PathVariable("username") String username, @PathVariable("songname") String songname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        model.addAttribute("username", username);
        model.addAttribute("loggedIn", logged);

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Song removeableSong = songServices.findByTitle(songname).get(0);
        userServices.removeSong(removeableSong, user);

        return "library_playlist_template";
    }
}
