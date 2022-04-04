package com.example.amplifyinternalservice.services;

import com.example.amplifyinternalservice.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;


@Service
public class SongDownloadService {

    @Autowired
    SongService ss;

    public ResponseEntity<ByteArrayResource> downloadSong(long id) throws SQLException {
        Optional<Song> s = ss.findById(id);
        if (s.isPresent()) {
            Song song = s.get();
            Blob file = song.getSongFile().getFile();
            System.out.println("Song " + song.getTitle() + " with id " + song.getId() + " and byte length " + (int) file.length() + " is downloading");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + song.getTitle().replace(" ", "_" )  + ".ogg\"")
                    .body(new ByteArrayResource(file.getBytes(1, (int) file.length())));

        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
