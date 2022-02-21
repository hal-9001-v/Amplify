package com.example.amplify.services;

import com.example.amplify.controllers.LoginController;
import com.example.amplify.model.*;
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

    public void AddSong(Song song, User user){

        List<Song> allUserSongs = user.getSongs();
        if(allUserSongs.contains(song))
        {return;}
        allUserSongs.add(song);
        user.setSongs((ArrayList<Song>) allUserSongs);
        userRepo.save(user);
    }

    public void AddPlaylist(Playlist playlist, User user){
        ArrayList<Playlist> allUserPlaylists = (ArrayList<Playlist>) user.getPlaylists();
        if(allUserPlaylists.contains(playlist))
        {return;}
        allUserPlaylists.add(playlist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);
    }
    public void AddArtist(Artist artist, User user){
        List<Artist> allUserArtists = user.getArtists();
        if(allUserArtists.contains(artist))
        {return;}
        allUserArtists.add(artist);
        user.setArtists((ArrayList<Artist>) allUserArtists);
        userRepo.save(user);

    }    public void AddAlbum(Album album, User user){
        List<Album> allUserAlbums = user.getAlbums();
        if(allUserAlbums.contains(album))
        {return;}
        allUserAlbums.add(album);
        user.setAlbums((ArrayList<Album>) allUserAlbums);
        userRepo.save(user);

    }


    public User checkLogin(HttpSession session, User user){
        user = (User) session.getAttribute(LoginController.UserSessionKey);
        if (user != null) {
            return user;
        }
        return null;
    }

}
