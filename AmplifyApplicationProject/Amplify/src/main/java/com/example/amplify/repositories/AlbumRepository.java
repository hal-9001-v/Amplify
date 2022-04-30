package com.example.amplify.repositories;

import com.example.amplify.model.Album;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Cacheable("albums")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Album> findByName(String name);

}
