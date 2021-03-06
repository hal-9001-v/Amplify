package com.example.amplify.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
public class Artist implements Serializable {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    private String name;

    //BD Relations
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Song> songs;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "artist", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Album> albums;


    //Construct
    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    //Attrib Get&Set
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public long getId() {return this.id;}

    //FK Get&Set
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    //Methods


}
