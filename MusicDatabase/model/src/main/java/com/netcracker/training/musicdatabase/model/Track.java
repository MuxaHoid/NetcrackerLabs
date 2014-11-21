package com.netcracker.training.musicdatabase.model;

import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MuxaHoid on 13.11.2014.
 */
@Entity
@Table(name="Track")
public class Track {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="track_id")
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "track_in_album", joinColumns = {
            @JoinColumn(name = "track_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id")})
    private Set<Album> albums = new HashSet<Album>();

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "track_title")
    private String title;

    @Column(name = "track_length")
    int length;

    public Track (){

    }

    public Track(Artist artist, Set<Album> albums, Genre genre, String title, int length) {
        this.artist = artist;
        this.albums = albums;
        this.genre = genre;
        this.title = title;
        this.length = length;
    }

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
