package com.example.amplify.services;


import com.example.amplify.model.Album;
import com.example.amplify.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlbumServices {

    @Autowired
    private AlbumRepository albumRepo;

    @Transactional(readOnly = true)
    public List<Album> findByName(String name) {
        return albumRepo.findByName(name);
    }

}
