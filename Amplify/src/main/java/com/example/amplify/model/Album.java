package com.example.amplify.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Album {


    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    private float length;
    private String name;

    //BD Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
    private List<Song> songs;

    @ManyToOne(cascade = CascadeType.ALL)
    Artist artist;

    //Construct
    public Album() {

    }

    public Album(String name) {
        this.name = name;

    }

    //Attrib Get&Set
    public float GetLength() {
        return this.length;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    //FK Get&Set
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    //Methods
    public float calculateTotalLength() {

        float length = 0;
        for (int i = 0; i < songs.size(); i++) {
            length += songs.get(i).getLength();
        }
        return length;
    }
}
