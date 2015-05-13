package com.netcracker.training.musicdatabase.service;

import com.netckracker.training.musicdatabase.db.AudioLibraryDAOImpl;
import com.netcracker.training.musicdatabase.model.Track;
import com.netcracker.training.musicdatabase.model.User;

/**
 * Created by MuxaHoid on 5/11/2015.
 */
public class VoteServiceImpl implements VoteService {
    public void voteForTrack(String trackID, String username, Integer rating) {
        AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
        User user = dao.getUserByName(username);
        Track ratedTrack = dao.getTrackByID(Long.parseLong(trackID));
        user.getVotes().put(ratedTrack,rating);
        dao.updateUser(user);
    }
}
