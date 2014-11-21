package com.netckracker.training.musicdatabase.controller;

import com.netckracker.training.musicdatabase.db.AudioLibraryDAOImpl;
import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;
import com.netcracker.training.musicdatabase.model.Genre;
import com.netcracker.training.musicdatabase.model.Track;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
public class Controller {
    public static Response getResult(Request request){
        Response response = new Response();

        if(request.type.equals("add")){
            AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
            Track track = new Track();
            track.setTitle(request.tracks.title);
            track.setGenre(new Genre(request.tracks.genre));
            track.setLength(Integer.parseInt(request.tracks.length));
            String[] albums = request.tracks.album.split(", ");
            for(int i = 0; i<albums.length;i++){
                track.getAlbums().add(new Album(albums[i]));
            }
            track.setArtist(new Artist(request.tracks.artist));
            dao.addTrack(track,albums);
        }
        else if(request.type.equals("set")){
            AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
            Track track = new Track();
            track.setId(Long.parseLong(request.tracks.id));
            track.setTitle(request.tracks.title);
            track.setGenre(new Genre(request.tracks.genre));
            track.setLength(Integer.parseInt(request.tracks.length));
            String[] albums = request.tracks.album.split(", ");
            track.setArtist(new Artist(request.tracks.artist));
            dao.updateTrack(track, albums);
        }
        else if(request.type.equals("get")){
            AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
            dao.getTracks(request.tracks.title,request.tracks.artist,request.tracks.album,request.tracks.genre);
        }
        else if(request.type.equals("remove")){
            AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();;
            dao.removeTrack(Long.parseLong(request.tracks.id));
        }
        return response;
    }
}
