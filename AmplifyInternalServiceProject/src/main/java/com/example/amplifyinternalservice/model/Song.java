package com.example.amplifyinternalservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;


@Entity
public class Song implements Serializable {

    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;


    @OneToOne (cascade = CascadeType.PERSIST, mappedBy = "song", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SongFile songFile;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return this.id;
    }
    public void setSongFile(SongFile songFile){this.songFile = songFile;}
    public SongFile getSongFile(){return songFile;}


}
