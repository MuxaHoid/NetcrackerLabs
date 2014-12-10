package com.netckracker.training.musicdatabase.controller;

import com.netckracker.training.musicdatabase.db.AudioLibraryDAOImpl;
import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Track;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
public class Controller {
    Request lastGetRequest = new Request();
    public Response getResult(Request request){
        Response response = new Response();
        AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
        List<Track> result = new ArrayList<Track>();
        if(request.type.equals("add")){
            dao = new AudioLibraryDAOImpl();
            Track track = new Track();
            track.setTitle(request.tracks.title);
            track.setGenre(dao.getGenreByName(request.tracks.genre));
            track.setLength(Integer.parseInt(request.tracks.time));
            String[] albums = request.tracks.album.split(", ");
            for(int i = 0; i<albums.length;i++){
                track.getAlbums().add(new Album(albums[i]));
            }
            track.setArtist(dao.getArtistByName(request.tracks.artist));
            dao.addTrack(track,albums);
            result = dao.getTracks(lastGetRequest.tracks.title,lastGetRequest.tracks.artist,lastGetRequest.tracks.album,lastGetRequest.tracks.genre);
        }
        else if(request.type.equals("set")){
            dao = new AudioLibraryDAOImpl();
            Track track = new Track();
            track.setId(Long.parseLong(request.tracks.id));
            track.setTitle(request.tracks.title);
            track.setGenre(dao.getGenreByName(request.tracks.genre));
            track.setLength(Integer.parseInt(request.tracks.time));
            String[] albums = request.tracks.album.split(", ");
            track.setArtist(dao.getArtistByName(request.tracks.artist));
            dao.updateTrack(track, albums);
            result = dao.getTracks(lastGetRequest.tracks.title,lastGetRequest.tracks.artist,lastGetRequest.tracks.album,lastGetRequest.tracks.genre);
        }
        else if(request.type.equals("get")){
            dao = new AudioLibraryDAOImpl();
            result = dao.getTracks(request.tracks.title,request.tracks.artist,request.tracks.album,request.tracks.genre);
            lastGetRequest = request;
        }
        else if(request.type.equals("remove")){
            dao = new AudioLibraryDAOImpl();;
            dao.removeTrack(Long.parseLong(request.tracks.id));
            result = dao.getTracks(lastGetRequest.tracks.title,lastGetRequest.tracks.artist,lastGetRequest.tracks.album,lastGetRequest.tracks.genre);
        }
        response.tracks = new ResultTrack[result.size()];
        for(int i = 0; i < response.tracks.length; i++){
            response.tracks[i] = new ResultTrack();
            response.tracks[i].artist=result.get(i).getArtist().getName();
            response.tracks[i].genre=result.get(i).getGenre().getName();
            response.tracks[i].id=result.get(i).getId().toString();
            response.tracks[i].title=result.get(i).getTitle();
            response.tracks[i].time =Integer.toString(result.get(i).getLength());
            Iterator<Album> iterator =result.get(i).getAlbums().iterator();
            response.tracks[i].album="";
            while (iterator.hasNext()){
                response.tracks[i].album+=iterator.next().getTitle()+", ";
            }
        }
        return response;
    }
}
