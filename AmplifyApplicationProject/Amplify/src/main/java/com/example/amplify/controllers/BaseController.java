package com.example.amplify.controllers;

import com.example.amplify.model.*;
import com.example.amplify.repositories.*;
import com.example.amplify.services.AlbumServices;
import com.example.amplify.services.ArtistServices;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;



@Controller
public class BaseController {

    @Autowired
    SongRepository songRepo;

    @Autowired
    SongServices songServices;


    //TESTING

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepo;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    ArtistServices artistServices;
    @Autowired
    AlbumServices albumServices;

    @RequestMapping({"/", "/inicio",})
    public String main(Model model) {

        String username = "";
        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails) sessionUser).getUsername();
            logged = true;
        }

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", username);

        model.addAttribute("songs", songServices.requestRecommendedSongs());
        return "main_template";
    }

    @RequestMapping({"inicio/{username}"})
    public String main_user(Model model, HttpSession session, @PathVariable String username) {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails) sessionUser).getUsername();
            logged = true;
        }

        model.addAttribute("loggedIn", logged);
        model.addAttribute("sessionusername", username);
        model.addAttribute("songs", songServices.requestRecommendedSongs());

        return "main_template";

    }

    @RequestMapping({"/statisticsMail"})
    public String sendStatisticMails(Model model) {
        return "mail_statistics_template";
    }

    @RequestMapping({"/recommendationsMail"})
    public String sendRecommendationsMails(Model model) {
        return "mail_recommendations_template";
    }

    @RequestMapping({"/loadDatabase"})
    public String dataBaseLoader(Model model) {
        User user = new User("dexaxi", passwordEncoder.encode("123"), "dexaxi12@gmail.com", "USER", "ADMIN");
        Artist artist = new Artist("Porta");
        Album album = new Album("DB-Rap");
        artistRepository.save(artist);
        albumRepository.save(album);

        for (int i = 0; i < 100; i++) {
            Song s = new Song("Cancion" + i, "CHILL");
            s.setAlbum(album);
            s.setArtist(artist);
            songRepo.save(s);
        }

        user.setSongs((ArrayList<Song>) songServices.findAll());
        userRepo.save(user);

        artist = artistServices.findByName("Porta").get(0);
        album = albumServices.findByName("DB-Rap").get(0);

        Playlist playlist = new Playlist("Noescuestiondeedades");
        playlist.setUser(user);
        Playlist playlist1 = new Playlist("Hevuelto");
        playlist1.setUser(user);
        ArrayList<Playlist> playlistlist = new ArrayList<Playlist>();
        playlistlist.add(playlist);
        playlistlist.add(playlist1);
        user.setPlaylists(playlistlist);
        ArrayList<Song> songList = new ArrayList<>();
        ArrayList<Song> songList1 = new ArrayList<>();
        for (int i = 0; i < songServices.findAll().size() / 4; i++) {
            songList.add(songServices.findAll().get(i));
            songList1.add(songServices.findAll().get(i + 25));

        }
        playlist.setSongs(songList);
        playlist1.setSongs(songList1);
        album.setSongs(songList1);
        album.setArtist(artist);
        albumRepository.save(album);
        ArrayList<Album> albumList = new ArrayList<Album>();
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        albumList.add(album);
        artistList.add(artist);
        user.setAlbums(albumList);
        user.setArtists(artistList);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist1);
        userRepo.save(user);

        return "main_template";
    }

}


