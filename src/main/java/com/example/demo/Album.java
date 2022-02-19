package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;

}
