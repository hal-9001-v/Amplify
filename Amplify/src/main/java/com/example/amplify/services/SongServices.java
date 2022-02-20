package com.example.amplify.services;

import com.example.amplify.model.Song;
import com.example.amplify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServices {
    @Autowired
    SongRepository songRepo;

    List<Song> findByTitle(String title) {
        return songRepo.findByTitle(title);
    }

    List<Song> findByLength(float length) {
        return songRepo.findByLength(length);
    }

}
