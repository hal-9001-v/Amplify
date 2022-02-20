package com.example.amplify;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Class attribs
    private String name;
    private float length;

    //BD Relations
    @Autowired
    @ManyToMany
    private List<Song> songs;


    @Autowired
    @OneToOne
    User user;


    //Construct
    public Playlist(){
        length = calculateTotalLength();
    }

    public Playlist(String name){
        this.name = name;
        length = calculateTotalLength();

    }


    //Attrib Get&Set
    public float GetLength(){return this.length;}
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    //FK Get&Set
    public List<Song> getSongs() {return songs;}
    public void setSongs(List<Song> songs){this.songs = songs;}

    public User getUser() {return this.user;}
    public void setUser(User user) {this.user = user;}


    //Methods
    public float calculateTotalLength(){

        float  length = 0;
        for (int i = 0; i < songs.size(); i++) {
            length+= songs.get(i).getLength();
        }
        return length;

    }

    public void addLength(float length){this.length += length;}

    public void AddSong(Song s){

        songs.add(s);
        addLength(s.getLength());

    }

}

