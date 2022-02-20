package com.example.amplify.services;


import com.example.amplify.model.Playlist;
import com.example.amplify.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServices {

    @Autowired
    PlaylistRepository playlistRepo;

    List<Playlist> findByName(String name) {
        return playlistRepo.findByName(name);
    }

    List<Playlist> findByLength(float length) {
        return playlistRepo.findByLength(length);
    }

}
