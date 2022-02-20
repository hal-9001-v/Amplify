package com.example.amplify;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    String username;
    String password;


    //BD Relations
    //Fav Playlists
    @Autowired
    @OneToMany
    List<Playlist> playlists;
    //Fav Artists
    @Autowired
    @OneToMany
    List<Artist> artists;
    //Fav songs
    @Autowired
    @OneToMany
    List<Song> songs;
    //Fav album
    @Autowired
    @OneToMany
    List<Album> albums;

    //Construct

    public User() {
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;

    }

    //Attrib Get&Set
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //FK Get&Set

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }


    //Methods


}
