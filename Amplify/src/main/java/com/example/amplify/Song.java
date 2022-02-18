package com.example.amplify;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@Entity
public class Song {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    //Class attribs
    private String title;
    private float length;
    // private Sound song;


    //BD Relations
    @Autowired
    @ManyToOne
    private Artist artist;

    @Autowired
    @OneToOne
    private Album album;


    //Construct

    public Song() {
        this.length = calculateLength();
    }

    public Song(String title){
        this.length = calculateLength();
        this.title = title;
    }

    //Attrib Get&Set
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public Float getLength(){return length;}


    //FK Get&Set
    public Artist getArtist() {return artist;}
    public void setArtist(Artist artist){this.artist = artist;}

    public Album getAlbum() {return album;}
    public void setAlbum(Album album){this.album = album;}


   //Methods

    private float calculateLength(){
        //TO DO
        //return Sound.length();
        return 1;
    }





}
