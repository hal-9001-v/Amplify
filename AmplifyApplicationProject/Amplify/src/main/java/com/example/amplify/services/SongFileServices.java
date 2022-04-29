package com.example.amplify.services;
import com.example.amplify.model.SongFile;
import com.example.amplify.repositories.SongFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongFileServices {

    @Autowired
    private SongFileRepository songFileRepo;

    public Optional<SongFile> findById(long id) {
        return songFileRepo.findById(id);
    }

    public List<SongFile> findAll() {
        return songFileRepo.findAll();
    }

}

