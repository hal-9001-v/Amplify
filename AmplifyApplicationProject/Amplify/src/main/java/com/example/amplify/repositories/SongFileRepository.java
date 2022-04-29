package com.example.amplify.repositories;

import java.util.Optional;

import com.example.amplify.model.SongFile;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongFileRepository extends JpaRepository<SongFile, Long> {

    @Cacheable("songs")
    Optional<SongFile> findById(long id);

}
