package com.example.amplify.services;

import com.example.amplify.model.Song;
import com.example.amplify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServices {
    @Autowired
    private SongRepository songRepo;

    @Transactional(readOnly = true)
    public List<Song> findByTitle(String title) {
        return songRepo.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public Optional<Song> findById(long id) {
        return songRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Song> findAll() {
        return songRepo.findAll();
    }

    public List<Song> requestRecommendedSongs() {

        List<Song> allSongs = findAll();
        ArrayList<Song> requestedSongs = new ArrayList<Song>();
        int listSize = 5;
        for (int i = 0; i < listSize; i++) {
            double random = Math.random() * listSize;
            Song unitSong = allSongs.get((int) random);

            for (Song s : requestedSongs) {
                if (s.getTitle().equals(unitSong.getTitle())) {
                    break;
                }
            }
            requestedSongs.add(unitSong);
        }

        return requestedSongs;
    }

}
