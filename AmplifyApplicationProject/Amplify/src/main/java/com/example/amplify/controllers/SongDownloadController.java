package com.example.amplify.controllers;


import com.example.amplify.internalService.FileDownloadService;
import com.example.amplify.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Controller
public class SongDownloadController {


    @Autowired
    FileDownloadService fds;

    @GetMapping(value = "/song/descargar/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> download(@PathVariable long id) throws SQLException, URISyntaxException {
        return fds.downloadSong(id);

    }



}
