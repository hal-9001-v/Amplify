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
    String userName;
    String password;
    

    //BD Relations
    //Fav Playlists
    @Autowired
    @OneToMany
    ArrayList<Playlist> favouritePlaylists;
    //Fav Artists
    @Autowired
    @OneToMany
    ArrayList<Artist> favouriteArtists;
    //Fav songs
    @Autowired
    @OneToMany
    ArrayList<Song> favouriteSongs;
    //Fav album
    @Autowired
    @OneToMany
    ArrayList<Album> favouriteAlbums;

    //Construct

    public User(){}

    public User(String userName, String password){

        this.userName = userName;
        this.password = password;

    }

    //Attrib Get&Set
    public String getUserName(){return this.userName;}
    public void setUserName(String name){this.userName = name;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}

    //FK Get&Set

    public ArrayList<Song> getFavouriteSongs() {return favouriteSongs;}
    public void setFavouriteSongs(ArrayList<Song> songs){this.favouriteSongs = songs;}

    public ArrayList<Album> getFavouriteAlbums() {return favouriteAlbums;}
    public void setFavouriteAlbums(ArrayList<Album> albums){this.favouriteAlbums = albums;}

    public ArrayList<Artist> getFavouriteArtists() {return favouriteArtists;}
    public void setFavouriteArtists(ArrayList<Artist> artists){this.favouriteArtists = artists;}

    public ArrayList<Playlist> getFavouritePlaylists() {return favouritePlaylists;}
    public void setFavouritePlaylists(ArrayList<Playlist> playlists){this.favouritePlaylists = playlists;}


    //Methods


}
