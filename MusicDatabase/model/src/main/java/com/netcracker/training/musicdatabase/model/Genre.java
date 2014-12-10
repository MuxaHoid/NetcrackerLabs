package com.netcracker.training.musicdatabase.model;

/**
 * Created by MuxaHoid on 19.11.2014.
 */

import javax.persistence.*;

/**
 * Created by MuxaHoid on 13.11.2014.
 */
@Entity
@Table(name="Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="genre_id")
    private Long id;
    @Column(name = "genre_name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Genre)obj).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}