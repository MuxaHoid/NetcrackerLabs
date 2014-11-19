package com.netcracker.training.musicdatabase.model;

import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;

import java.util.Set;

/**
 * Created by MuxaHoid on 13.11.2014.
 */
public class Track {
    Long id;
    Artist artist;
    Set<Album> albums;
    Genre genre;
    String title;
    int length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
