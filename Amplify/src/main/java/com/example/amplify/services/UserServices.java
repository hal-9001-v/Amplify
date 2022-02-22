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

    @Autowired
    PlaylistServices playlistServices;

    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByPassword(String password) {
        return userRepo.findByPassword(password);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public List<User> findAllByUsername(String username, Pageable page) {

        return userRepo.findAllByUsername(username, page);

    }

    public void addSong(Song song, User user) {

        List<Song> allUserSongs = user.getSongs();

        if(user.getSongs() != null) {
            for (Song s : user.getSongs()) {
                if (s.getTitle().equals(song.getTitle())) return;
            }
        } else {
            allUserSongs = new ArrayList<Song>();
        }

        allUserSongs.add(song);
        user.setSongs(allUserSongs);
        userRepo.save(user);

    }

    public void addPlaylist(Playlist playlist, User user) {

        List<Playlist> allUserPlaylists = user.getPlaylists();

        if (user.getPlaylists() != null) {
            for (Playlist p : user.getPlaylists()) {
                if (p.getName().equals(playlist.getName())) return;
            }
        } else {
            allUserPlaylists = new ArrayList<Playlist>();
        }

        allUserPlaylists.add(playlist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);

    }

    public void addArtist(Artist artist, User user) {
        List<Artist> allUserArtists = user.getArtists();

        if(user.getArtists() != null) {
            for (Artist a : user.getArtists()) {
                if (a.getName().equals(artist.getName())) return;
            }
        } else {
            allUserArtists = new ArrayList<Artist>();
        }
        allUserArtists.add(artist);
        user.setArtists(allUserArtists);
        userRepo.save(user);


    }

    public void addAlbum(Album album, User user) {
        List<Album> allUserAlbums = user.getAlbums();

        if(user.getAlbums() !=null) {
            for (Album a : user.getAlbums()) {
                if (a.getName().equals(album.getName())) return;
            }
        } else {
            allUserAlbums = new ArrayList<Album>();
        }

        allUserAlbums.add(album);
        user.setAlbums(allUserAlbums);
        userRepo.save(user);

    }

    public void removePlaylist(Playlist playlist, User user) {

        List<Playlist> allUserPlaylists = user.getPlaylists();
        for (Playlist p : allUserPlaylists) {
            if (p.getName().equals(playlist.getName())) {
                allUserPlaylists.remove(playlist);
                user.setPlaylists(allUserPlaylists);
                userRepo.save(user);
            }
        }

    }


    public User checkLogin(HttpSession session) {
        User user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {
            //System.out.println(user.getUsername());
            return user;
        }
        return null;
    }

}
