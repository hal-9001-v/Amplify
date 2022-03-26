package com.example.amplify.controllers;

import com.example.amplify.repositories.*;
import com.example.amplify.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

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
    PlaylistRepository playlistRepository;

    @Autowired
    SongServices songServices;
    @Autowired
    UserServices userServices;
    @Autowired
    ArtistServices artistServices;
    @Autowired
    AlbumServices albumServices;
    @Autowired
    PlaylistServices playlistServices;

    @PostConstruct
    public void init() {

        //userRepo.deleteAll();
        //songRepo.deleteAll();
        //artistRepository.deleteAll();
        //albumRepository.deleteAll();
        //playlistRepository.deleteAll();

        /*
        User user = new User("dexaxi", "123");
        Artist artist = new Artist("Porta");
        Album album = new Album("DB-Rap");
        artistRepository.save(artist);
        albumRepository.save(album);

        for (int i = 0; i < 100; i++) {
            Song s = new Song("Cancion" + i);
            s.setAlbum(album);
            s.setArtist(artist);
            songRepo.save(s);
        }

        user.setSongs((ArrayList<Song>) songServices.findAll());
        userRepo.save(user);

        artist = artistServices.findByName("Porta").get(0);
        album = albumServices.findByName("DB-Rap").get(0);
        user = userServices.findByUsername("dexaxi").get(0);
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
        */

        /* User Vicente = userServices.findByUsername("Vicente").get(0);
        User dexaxi = userServices.findByUsername("dexaxi").get(0);
        Playlist laquesea = dexaxi.getPlaylists().get(0);
        userServices.addPlaylist(laquesea, Vicente);*/
    }

}
