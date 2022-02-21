package com.example.amplify.services;

import com.example.amplify.controllers.LoginController;
import com.example.amplify.model.*;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PlaylistRepository playlistRepo;

    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByPassword(String password) {
        return userRepo.findByPassword(password);
    }
    public List<User> findAll(){return userRepo.findAll();}

    public List<User> findAllByUsername(String username, Pageable page) {

        return userRepo.findAllByUsername(username, page);

    }

    public void addSong(Song song, User user){

        List<Song> allUserSongs = user.getSongs();

        for (Song s: user.getSongs()) {
            if(s.getTitle().equals(song.getTitle())) return;
        }

        allUserSongs.add(song);
        user.setSongs(allUserSongs);
        userRepo.save(user);

    }

    public void addPlaylist(Playlist playlist, User user){

        List<Playlist> allUserPlaylists = user.getPlaylists();

        for (Playlist p: user.getPlaylists()) {
            if(p.getName().equals(playlist.getName())) return;
        }

        allUserPlaylists.add(playlist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);

    }

    public void addArtist(Artist artist, User user){
        List<Artist> alUserArtists = user.getArtists();

        for (Artist a: user.getArtists()) {
            if(a.getName().equals(artist.getName())) return;
        }

        alUserArtists.add(artist);
        user.setArtists(alUserArtists);
        userRepo.save(user);


    }
    public void addAlbum(Album album, User user){
        List<Album> allUSerAlbums = user.getAlbums();

        for (Album a: user.getAlbums()) {
            if(a.getName().equals(album.getName())) return;
        }

        allUSerAlbums.add(album);
        user.setAlbums(allUSerAlbums);
        userRepo.save(user);

    }

    public void removePlaylist(Playlist playlist, User user){

        List<Playlist> allUserPlaylists = user.getPlaylists();

        for (Playlist p: user.getPlaylists()) {

        }

        allUserPlaylists.add(playlist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);


    }


    public User checkLogin(HttpSession session){
        User user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {
            return user;
        }
        return null;
    }

}
