package com.example.amplify.services;


import com.example.amplify.model.Playlist;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServices {

    @Autowired
    private PlaylistRepository playlistRepo;

    public List<Playlist> findByName(String name) {
        return playlistRepo.findByName(name);
    }

    public List<Playlist> findByLength(float length) {
        return playlistRepo.findByLength(length);
    }

    public Playlist createPlaylist(String name, User user){

        Playlist newPlaylist = new Playlist(name);
        newPlaylist.setUser(user);
        System.out.println(newPlaylist.getUser().getUsername());

        for (Playlist p: playlistRepo.findAll()) {
            if(p.getName().equals(newPlaylist.getName()))

                return p;
        }
        playlistRepo.save(newPlaylist);

        return newPlaylist;
    }

    public void addSong(Song song, Playlist playlist){

            List<Song>  playlistSongs = playlist.getSongs();

            for (Song s: playlistSongs) {
            if(s.getTitle().equals(song.getTitle())) return;
            }

            playlistSongs.add(song);
            playlist.setSongs(playlistSongs);
            playlistRepo.save(playlist);

    }

}
