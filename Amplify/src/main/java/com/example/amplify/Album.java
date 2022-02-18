package com.example.amplify;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


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
    @Autowired
    @OneToMany
    private ArrayList<Song> songs;

    @Autowired
    @ManyToOne
    Artist author;


    //Construct
    public Album(){
        length = calculateTotalLength();
    }

    public Album(String name){
        this.name = name;
        length = calculateTotalLength();

    }

    //Attrib Get&Set
    public float GetLength(){return this.length;}
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    //FK Get&Set
    public ArrayList<Song> getSongs() {return songs;}
    public void setSongs(ArrayList<Song> songs){this.songs = songs;}

    public Artist getAuthor() {return author;}
    public void setAuthor(Artist author){this.author = author;}

    //Methods
    public float calculateTotalLength(){

        float  length = 0;
        for (int i = 0; i < songs.size(); i++) {
            length+= songs.get(i).getLength();
        }
        return length;
    }


}
