package com.example.amplify.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playlist {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    private String name;
    private float length;

    //BD Relations
    @OneToMany
    private List<Song> songs;

    @ManyToMany(cascade = CascadeType.ALL,  mappedBy = "playlists")
    private List<User> users;

    @OneToOne
    private User user;


    //Construct
    public Playlist() {

    }

    public Playlist(String name) {
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
    public long getId() {return this.id;}

    //FK Get&Set
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> songs) {
        this.users = users;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    //Methods
    public float calculateTotalLength() {

        float length = 0;
        for (int i = 0; i < songs.size(); i++) {
            length += songs.get(i).getLength();
        }
        return length;

    }

    public void addLength(float length) {
        this.length += length;
    }

    public void AddSong(Song s) {

        songs.add(s);
        addLength(s.getLength());

    }

}

