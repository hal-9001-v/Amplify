package com.example.amplify.controllers;


import com.example.amplify.internalService.FileDownloadService;
import com.example.amplify.model.Song;
import com.example.amplify.model.User;
import com.example.amplify.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    UserServices userServices;

    @GetMapping(value = "/song/descargar/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> downloadSong(Model model, @PathVariable long id) throws URISyntaxException {

        boolean logged = false;
        Object sessionUser = UserServices.checkLogged();

        if (sessionUser instanceof UserDetails) {
            String sessionUsername = ((UserDetails) sessionUser).getUsername();
            model.addAttribute("sessionusername", sessionUsername);
            logged = true;
            userServices.UpdateDownloads(userServices.findByUsername(sessionUsername).get());
        }

        model.addAttribute("loggedIn", logged);


        return fds.downloadSong(id);

    }


}
