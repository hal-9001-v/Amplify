package com.example.amplify.controllers;

import com.example.amplify.model.Playlist;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.repositories.UserRepository;
import com.example.amplify.services.PlaylistServices;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class PlaylistController {

    @Autowired
    PlaylistRepository playlistRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    PlaylistServices playlistServices;
    @Autowired
    UserServices userServices;
    @Autowired
    SongServices songServices;

    @RequestMapping("/crear-playlist")
    public String viewCreatePlaylistWindow(Model model) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());
        return "new_playlist_template";
    }

    @RequestMapping("/playlist/anadirNuevaPlaylist")
    public String createPlaylist(Model model, @RequestParam String playlistName) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());

        //Create playlist
        Playlist newPLaylist = playlistServices.createPlaylist(playlistName, user);
        userServices.addPlaylist(newPLaylist, user);

        model.addAttribute("playlist", newPLaylist);

        return "main_template";
    }

    @RequestMapping("/playlist/{playlistName}")
    public String viewPlaylist(Model model, @PathVariable("playlistName") String playlistName) {

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

        Playlist requestedPlaylist = playlistServices.findByName(playlistName).get(0);
        model.addAttribute("playlist", requestedPlaylist);
        model.addAttribute("songs", requestedPlaylist.getSongs());
        return "playlist_template";
    }

    @RequestMapping("/playlist/{playlistName}/eliminarPlaylist")
    public String deletePlaylist(Model model, @PathVariable String playlistName) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());

        List<Playlist> list = playlistServices.findByName(playlistName);
        if (!list.isEmpty()) {
            Playlist deletablePlaylist = list.get(0);
            playlistRepo.delete(playlistServices.findByName(deletablePlaylist.getName()).get(0));
        }

        return "main_template";
    }

    @RequestMapping("/playlist/anadir/{songtitle}")
    public String addToPlaylistScreen(Model model, @PathVariable String songtitle) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());

        List<Playlist> playliststList = user.getPlaylists();
        model.addAttribute("playlists", playliststList);
        model.addAttribute("songtitle", songtitle);
        return "display_owned_playlists_template";
    }

    @RequestMapping("/playlist/anadir/{songtitle}/{playlistname}")
    public String addToPlaylist(Model model, @PathVariable("songtitle") String songtitle, @PathVariable("playlistname") String playlistname) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());

        Song songToAdd = songServices.findByTitle(songtitle).get(0);
        Playlist playlistToAddTo = playlistServices.findByName(playlistname).get(0);
        playlistServices.addSong(songToAdd, playlistToAddTo);

        return "main_template";
    }

    @RequestMapping("/playlist/{playlistname}/quitar/{songtitle}")
    public String removeFromPlaylist(Model model, @PathVariable("playlistname") String playlistname, @PathVariable("songtitle") String songtitle) {

        String sessionUsername = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
        }

        User user = userServices.findByUsername(sessionUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", user.getUsername());

        Song songToRemove = songServices.findByTitle(songtitle).get(0);
          Playlist playlistToRemoveFrom = playlistServices.findByName(playlistname).get(0);
        playlistServices.removeSong(songToRemove, playlistToRemoveFrom);


        return "main_template";
    }
}
