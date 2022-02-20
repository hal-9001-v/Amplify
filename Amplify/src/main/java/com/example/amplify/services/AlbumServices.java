package com.example.amplify.services;


import com.example.amplify.model.Album;
import com.example.amplify.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServices {

    @Autowired
    AlbumRepository albumRepo;

    List<Album> findByName(String name) {
        return albumRepo.findByName(name);
    }

}
