package com.example.amplify.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    private String username;
    private String encodedPassword;
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    //BD Relations

    //Fav Playlists
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Playlist> playlists;
    //Fav Artists
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Artist> artists;
    //Fav songs
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Song> songs;
    //Fav album
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Album> albums;

    //Construct
    public User() {
    }

    public User(String username, String password, String email, String roles) {
        this.username = username;
        this.encodedPassword = password;
        this.email = email;
        this.roles = List.of(roles);
    }

    public Boolean isPassword(String password) {
        return this.encodedPassword.equals(password);
    }

    //Attrib Get&Set
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEncodedPassword() {
        return this.encodedPassword;
    }

    public void setEncodedPassword(String password) {
        this.encodedPassword = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
