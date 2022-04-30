package com.example.amplify.repositories;

import java.util.Optional;

import com.example.amplify.model.SongFile;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;


public interface SongFileRepository extends JpaRepository<SongFile, Long> {

    @Cacheable("songfiles")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<SongFile> findById(long id);

}
