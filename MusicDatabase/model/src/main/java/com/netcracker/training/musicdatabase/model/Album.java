package com.netcracker.training.musicdatabase.model;

import javax.persistence.*;

/**
 * Created by MuxaHoid on 13.11.2014.
 */


@Entity
@Table(name="Album")
public class Album {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="album_id")
    private Long id;

    @Column(name="album_title")
    private String title;

    public Album(String title) {
        this.title = title;
    }

    public Album(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getTitle().equals(((Album)obj).getTitle());
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
