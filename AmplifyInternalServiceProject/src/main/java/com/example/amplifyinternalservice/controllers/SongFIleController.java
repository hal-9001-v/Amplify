package com.example.amplifyinternalservice.controllers;


import com.example.amplifyinternalservice.services.SongDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
public class SongFIleController {

    @Autowired
    private SongDownloadService sds;

    @GetMapping("/song/{id}")
    public ResponseEntity<ByteArrayResource> downloadSongFile(@PathVariable long id) throws SQLException {
            return sds.downloadSong(id);
    }


}
