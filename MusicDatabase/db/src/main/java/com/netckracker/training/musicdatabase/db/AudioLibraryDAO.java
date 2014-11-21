package com.netckracker.training.musicdatabase.db;

import com.netcracker.training.musicdatabase.model.*;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by MuxaHoid on 18.11.2014.
 */
public interface AudioLibraryDAO {
    public List<Track> getTracks(String title, String artist, String album, String genre);

    public void updateTrack(Track track, String[] albums);

    public void addTrack(Track track,String[] albums);

    public void removeTrack (Long trackId);

}
