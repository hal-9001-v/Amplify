package com.example.amplify.repositories;

import java.util.List;
import java.util.Optional;

import com.example.amplify.model.Song;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;


public interface SongRepository extends JpaRepository<Song, Long> {

    @Cacheable("songs")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Song> findByTitle(String title);

    @Cacheable("songs")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Song> findById(long id);

}
