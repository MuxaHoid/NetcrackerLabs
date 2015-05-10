package com.netcracker.training.musicdatabase.service;

import com.netckracker.training.musicdatabase.db.AudioLibraryDAOImpl;
import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;
import com.netcracker.training.musicdatabase.model.Track;

import java.util.List;

/**
 * Created by MuxaHoid on 5/6/2015.
 */
public class ServiceImpl implements Service {
    AudioLibraryDAOImpl dao;

    public ServiceImpl() {
        dao = new AudioLibraryDAOImpl();
    }

    @Override
    public List<Track> getTracks() {
        return dao.getTracks("", "", "", "");
    }

    @Override
    public Track getTrackByID(Long id) {
        return dao.getTrackByID(id);
    }

    @Override
    public List<Track> getTracksByAlbum(Long id) {
        return dao.getTrackByAlbum(id);
    }

    @Override
    public List<Track> getTracksByArtist(Long id) {
        return dao.getTrackByArtist(id);
    }

    @Override
    public List<Track> getTracksByGenre() {
        return null;
    }

    @Override
    public List<Track> getTracksWithParameters() {
        return null;
    }

    public void removeTrack(Long id) {
        dao.removeTrack(id);
    }

    public void removeArtist(Long id) {
        dao.removeArtist(id);
    }

    public void removeAlbum(Long id) {
        dao.removeAlbum(id);
    }

    @Override
    public List<Artist> getArtists() {
        return dao.getArtistsByName("");
    }

    @Override
    public List<Album> getAlbums() {
        return dao.getAlbumsByTitle("");
    }

    public Album getAlbumByID(Long id) {
        return dao.getAlbumByID(id);
    }

    @Override
    public List<Album> getAlbumsByArtist() {
        return null;
    }

    @Override
    public List<Album> getAlbumsWithParameters() {
        return null;
    }

    @Override
    public Artist getArtistByName(String name) {
        return dao.getArtistByName(name);
    }

    public Artist getArtistByID(Long id) {
        return dao.getArtistByID(id);
    }

    @Override
    public void addTrack(String title, String albums, String artist, String genre, String length) {
        Track newTrack = new Track();
        newTrack.setTitle(title);
        newTrack.setGenre(dao.getGenreByName(genre));
        newTrack.setLength(Integer.parseInt(length));
        newTrack.setArtist(dao.getArtistByName(artist));
        String[] newAlbums = albums.split(";");
        for (int i = 0; i < newAlbums.length; i++) {
            newTrack.getAlbums().add(new Album(newAlbums[i]));
        }
        dao.addTrack(newTrack, newAlbums);
    }

    @Override
    public void addAlbum(String title) {
        Album a = new Album(title);
        dao.addAlbum(a);
    }

    @Override
    public void addArtist(String name) {
        Artist a = new Artist(name);
        dao.addArtist(a);
    }

    @Override
    public void editTrack(String id, String title, String albums, String artist, String genre, String length) {
        Track newTrack = getTrackByID(Long.parseLong("id"));
        newTrack.setTitle(title);
        newTrack.setGenre(dao.getGenreByName(genre));
        newTrack.setLength(Integer.parseInt(length));
        newTrack.setArtist(dao.getArtistByName(artist));
        String[] newAlbums = albums.split(";");
        dao.updateTrack(newTrack, newAlbums);
    }

    @Override
    public void editAlbum(String id, String title) {
        Album a = dao.getAlbumByID(Long.parseLong(id));
        a.setTitle(title);
        dao.updateAlbum(a);
    }

    @Override
    public void editArtist(String id, String name) {
        Artist a = dao.getArtistByID(Long.parseLong(id));
        a.setName(name);
        dao.updateArtist(a);
    }


}
