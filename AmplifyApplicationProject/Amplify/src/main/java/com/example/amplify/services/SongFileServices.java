package com.example.amplify.services;
import com.example.amplify.model.SongFile;
import com.example.amplify.repositories.SongFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongFileServices {

    @Autowired
    private SongFileRepository songFileRepo;

    @Transactional(readOnly = true)

    public Optional<SongFile> findById(long id) {
        return songFileRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<SongFile> findAll() {
        return songFileRepo.findAll();
    }

}

