package com.example.amplify.services;


import com.example.amplify.model.Album;
import com.example.amplify.model.Artist;
import com.example.amplify.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServices {

    @Autowired
    private ArtistRepository artistRepo;

    public List<Artist> findByName(String name) {
        return artistRepo.findByName(name);
    }

    public void addAlbum(Album album, Artist artist) {
        List<Album> allUserAlbums = artist.getAlbums();

        if (artist.getAlbums() != null) {
            for (Album a : artist.getAlbums()) {
                if (a.getName().equals(album.getName())) return;
            }
        } else {
            allUserAlbums = new ArrayList<Album>();
        }
        allUserAlbums.add(album);
        artist.setAlbums(allUserAlbums);
        artistRepo.save(artist);
    }
}
