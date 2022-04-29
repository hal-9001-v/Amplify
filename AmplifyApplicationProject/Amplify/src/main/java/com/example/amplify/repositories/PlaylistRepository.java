package com.example.amplify.repositories;

import com.example.amplify.model.Playlist;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Cacheable("playlists")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Playlist> findByName(String name);

    @Cacheable("playlists")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Playlist> findByLength(float length);

    @CacheEvict(value = "playlists")
    Playlist save(Playlist newPlaylist);

}
