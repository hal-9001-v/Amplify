package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class DataBaseHelper implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DataBaseHelper.class);


    @Autowired
    private AlbumRepository repository;

    @Override
    public void run(String... args) throws Exception {

    }
}
