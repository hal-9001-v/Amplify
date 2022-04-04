package com.example.amplify.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;


@Entity
public class Song {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    private String title;
    private String genre;

    @OneToOne (cascade = CascadeType.REMOVE, mappedBy = "song")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SongFile songFile;


    //BD Relations
    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Album album;


    //Construct
    public Song() {
    }

    public Song(String title, String genre, SongFile songFile) {
        this.title = title;
        this.genre = genre;
        this.songFile = songFile;
    }

    //Attrib Get&Set
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }
    public SongFile getSongFile(){return songFile;}

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //FK Get&Set
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setSongFile(SongFile songFile){this.songFile = songFile;}

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
