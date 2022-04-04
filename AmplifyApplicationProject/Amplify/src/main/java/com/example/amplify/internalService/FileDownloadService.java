package com.example.amplify.internalService;


import com.example.amplify.services.SongServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class FileDownloadService {
    @Autowired
    SongServices ss;

    public ResponseEntity<ByteArrayResource> downloadSong(long id) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI url = new URI("http://localhost:8080/song/" + id);
        return restTemplate.getForEntity(url, ByteArrayResource.class);
    }
}
