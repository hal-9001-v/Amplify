package com.example.amplify;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

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
    ArrayList<Playlist> playlists;
    //Fav Artists
    @Autowired
    @OneToMany
    ArrayList<Artist> artists;
    //Fav songs
    @Autowired
    @OneToMany
    ArrayList<Song> songs;
    //Fav album
    @Autowired
    @OneToMany
    ArrayList<Album> albums;

    //Construct

    public User(){}

    public User(String username, String password){

        this.username = username;
        this.password = password;

    }

    //Attrib Get&Set
    public String getUsername(){return this.username;}
    public void setUsername(String name){this.username = name;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}

    //FK Get&Set

    public ArrayList<Song> getSongs() {return songs;}
    public void setSongs(ArrayList<Song> songs){this.songs = songs;}

    public ArrayList<Album> getAlbums() {return albums;}
    public void setAlbums(ArrayList<Album> albums){this.albums = albums;}

    public ArrayList<Artist> getArtists() {return artists;}
    public void setArtists(ArrayList<Artist> artists){this.artists = artists;}

    public ArrayList<Playlist> getPlaylists() {return playlists;}
    public void setPlaylists(ArrayList<Playlist> playlists){this.playlists = playlists;}


    //Methods


}
