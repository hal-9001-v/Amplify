package com.example.amplify;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.*;


@Entity
public class Artist {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    String name;

    //BD Relations
    @Autowired
    @OneToMany
    ArrayList<Song> songs;

    @Autowired
    @OneToMany
    ArrayList<Album> albums;


    //Construct
    public Artist(){}

    public Artist(String name){
        this.name = name;
    }

    //Attrib Get&Set
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    //FK Get&Set
    public ArrayList<Song> getSongs() {return songs;}
    public void setSongs(ArrayList<Song> songs){this.songs = songs;}

    public ArrayList<Album> getAlbums() {return albums;}
    public void setAlbum(ArrayList<Album> albums){this.albums = albums;}

    //Methods



}
