package com.example.amplifyinternalservice.repos;


import com.example.amplifyinternalservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findByTitle(String title);
    Optional<Song> findById(long id);
}
