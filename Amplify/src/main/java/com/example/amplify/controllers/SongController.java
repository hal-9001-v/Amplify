package com.example.amplify.controllers;

import com.example.amplify.model.Album;
import com.example.amplify.model.Artist;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.AlbumRepository;
import com.example.amplify.repositories.ArtistRepository;
import com.example.amplify.repositories.SongRepository;
import com.example.amplify.repositories.UserRepository;
import com.example.amplify.services.AlbumServices;
import com.example.amplify.services.ArtistServices;
import com.example.amplify.services.SongServices;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SongController {

    @Autowired
    SongRepository songRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongServices songServices;

    @Autowired
    UserServices userServices;
    @Autowired
    ArtistServices artistServices;
    @Autowired
    AlbumServices albumServices;

    @PostConstruct
    public void init() {

        /*userRepo.deleteAll();
        songRepo.deleteAll();
        artistRepository.deleteAll();
        albumRepository.deleteAll();*/

      // User user = new User("dexaxi","123");
       /*Artist artist = new Artist("Porta");
       Album album = new Album("DB - Rap");
        artistRepository.save(artist);
        albumRepository.save(album);

        for (int i = 0; i < 100; i++) {
            Song s = new Song("Cancion" + i);
            s.setAlbum(album);
            s.setArtist(artist);
            songRepo.save(s);

        }*/

      //  user.setSongs((ArrayList<Song>) songServices.findAll());
       // userRepo.save(user);

        //Artist artist = artistServices.findByName("Porta").get(0);
        //Album album = albumServices.findByName("DB - Rap").get(0);

    }



}
