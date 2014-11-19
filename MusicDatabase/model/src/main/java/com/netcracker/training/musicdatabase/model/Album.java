package com.netcracker.training.musicdatabase.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by MuxaHoid on 13.11.2014.
 */


@Entity
@Table(name="Album")
public class Album {
    Long id;
    String title;

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
}
