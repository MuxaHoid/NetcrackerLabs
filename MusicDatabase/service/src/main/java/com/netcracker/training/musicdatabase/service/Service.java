package com.netcracker.training.musicdatabase.service;

import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;
import com.netcracker.training.musicdatabase.model.Track;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by MuxaHoid on 5/6/2015.
 */
@Remote
public interface Service {
    List<Track> getTracks();

    Track getTrackByID(Long id);

    List<Track> getTracksByAlbum(Long id);

    List<Track> getTracksByArtist(Long id);

    List<Track> getTracksByGenre();

    List<Track> getTracksWithParameters();

    void removeTrack(Long id);

    void removeArtist(Long id);

    void removeAlbum(Long id);

    List<Album> getAlbums();

    List<Artist> getArtists();

    Album getAlbumByID(Long id);

    List<Album> getAlbumsByArtist();

    List<Album> getAlbumsWithParameters();

    Artist getArtistByName(String name);

    Artist getArtistByID(Long id);

    void addArtist(String name);

    void addAlbum(String title);

    void addTrack(String title, String albums, String artist, String genre, String length);

    void editTrack(String id, String title, String albums, String artist, String genre, String length);

    void editAlbum(String id, String title);

    void editArtist(String id, String name);
}
