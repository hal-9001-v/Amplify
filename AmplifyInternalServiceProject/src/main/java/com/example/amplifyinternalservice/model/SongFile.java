package com.example.amplifyinternalservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
public class SongFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @JsonIgnore
    private Blob file;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Song song;


    public SongFile(Blob file, Song song){
        this.file = file;
        this.song = song;
    }

    public SongFile() {

    }


    public long getId(){return id;}
    public Blob getFile(){return file;}
    public void setFile(Blob file){this.file = file;}
    public Song getSong(){return song;}
    public void setSong(Song song){this.song=song;}
}
