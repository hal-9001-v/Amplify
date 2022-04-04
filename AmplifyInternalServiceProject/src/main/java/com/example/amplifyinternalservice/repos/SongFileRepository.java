package com.example.amplifyinternalservice.repos;


import java.util.Optional;

import com.example.amplifyinternalservice.model.SongFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongFileRepository extends JpaRepository<SongFile, Long> {
    Optional<SongFile> findById(long id);
}
