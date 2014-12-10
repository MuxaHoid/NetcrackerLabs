package com.netcracker.training.musicdatabase.model;

import javax.persistence.*;

/**
 * Created by MuxaHoid on 13.11.2014.
 */
@Entity
@Table(name="Artist")
public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="artist_id")
    private Long id;
    @Column(name="artist_name")
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public Artist(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Artist)obj).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
