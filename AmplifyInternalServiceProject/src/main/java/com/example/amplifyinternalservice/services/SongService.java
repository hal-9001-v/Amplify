package com.example.amplifyinternalservice.services;


import com.example.amplifyinternalservice.model.Song;
import com.example.amplifyinternalservice.repos.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepo;


    public Optional<Song> findById(long id){return songRepo.findById(id);}
}
