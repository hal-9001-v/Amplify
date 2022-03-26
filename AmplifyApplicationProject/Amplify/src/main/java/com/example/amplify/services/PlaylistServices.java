package com.example.amplify.services;


import com.example.amplify.model.Playlist;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        for (Playlist p: playlistRepo.findAll()) {
            if(p.getName().equals(newPlaylist.getName())) {
                return p;
            }
        }

        playlistRepo.save(newPlaylist);

        return newPlaylist;
    }

    public void addSong(Song song, Playlist playlist){

            List<Song>  playlistSongs = playlist.getSongs();

            if(playlist.getSongs() !=null) {
                for (Song s : playlistSongs) {
                    if (s.getTitle().equals(song.getTitle())) return;
                }
            }
            else {
                playlistSongs = new ArrayList<Song>();
            }

            playlistSongs.add(song);
            playlist.setSongs(playlistSongs);
            playlistRepo.save(playlist);

    }

    public void removeSong(Song song, Playlist playlist){

        List<Song> allPlaylistSongs = playlist.getSongs();
        Song removedSong = null;
        if(playlist.getSongs() != null) {
            for (Song s : allPlaylistSongs) {
                if (s.getTitle().equals(song.getTitle())) {
                    removedSong= s;
                }
            }

        }
        if(removedSong == null) return;
        allPlaylistSongs.remove(removedSong);
        playlist.setSongs(allPlaylistSongs);
        playlistRepo.save(playlist);

    }

}
