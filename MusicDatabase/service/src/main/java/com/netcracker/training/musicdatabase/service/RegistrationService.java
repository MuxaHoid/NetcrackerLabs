package com.netcracker.training.musicdatabase.service;
import javax.ejb.Remote;


/**
 * Created by MuxaHoid on 5/11/2015.
 */
@Remote
public interface RegistrationService {
    public boolean register(String username, String password);
    public boolean authenticate(String username, String password);
}
