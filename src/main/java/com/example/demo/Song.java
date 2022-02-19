package com.example.demo;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    int id;

    String name;
    String cover;
    Album album;
}
