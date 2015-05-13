package com.netcracker.training.musicdatabase.service;

/**
 * Created by MuxaHoid on 5/11/2015.
 */
public interface VoteService {
    public void voteForTrack(String trackID, String username, Integer rating);
}
