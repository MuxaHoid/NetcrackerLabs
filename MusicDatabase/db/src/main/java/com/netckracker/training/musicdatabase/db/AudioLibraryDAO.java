package com.netckracker.training.musicdatabase.db;

import com.netcracker.training.musicdatabase.model.*;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by MuxaHoid on 18.11.2014.
 */
public interface AudioLibraryDAO {
    List<Track> getTracks(String title, String artist, String album, String genre);

    void updateTrack(Track track, String[] albums);

    void addTrack(Track track, String[] albums);

    void removeTrack(Long trackId);

    User getUser(Long userID);
    User getUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long userID);

}
