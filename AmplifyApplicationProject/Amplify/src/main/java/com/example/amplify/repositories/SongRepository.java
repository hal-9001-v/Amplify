package com.example.amplify.repositories;

import com.example.amplify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTitle(String title);

    List<Song> findByLength(float length);

}
