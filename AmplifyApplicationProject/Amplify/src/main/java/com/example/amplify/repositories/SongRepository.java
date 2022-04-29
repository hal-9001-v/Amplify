package com.example.amplify.repositories;

import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByTitle(String title);

    Optional<Song> findById(long id);

}
