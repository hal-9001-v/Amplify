package com.example.amplify.repositories;

import com.example.amplify.model.Artist;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Cacheable("artists")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Artist> findByName(String username);

}
