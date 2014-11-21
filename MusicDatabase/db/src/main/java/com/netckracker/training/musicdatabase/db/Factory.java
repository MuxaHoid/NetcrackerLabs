package com.netckracker.training.musicdatabase.db;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
public class Factory {
    public AudioLibraryDAO getAudioLibraryDAO(){
        return new AudioLibraryDAOImpl();
    }
}
