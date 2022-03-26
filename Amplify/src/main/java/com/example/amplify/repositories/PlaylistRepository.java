package com.example.amplify.repositories;

import com.example.amplify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByName(String name);

    List<Playlist> findByLength(float length);

}
