package com.example.amplify.services;


import com.example.amplify.model.Artist;
import com.example.amplify.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServices {

    @Autowired
    ArtistRepository artistRepo;

    List<Artist> findByName(String name) {
        return artistRepo.findByName(name);
    }

}
