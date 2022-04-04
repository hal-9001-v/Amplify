package com.example.amplifyinternalservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Blob;

@Entity
public class SongFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @JsonIgnore
    private Blob file;

    @OneToOne
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
