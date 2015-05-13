package com.netcracker.training.musicdatabase.service;

import com.netckracker.training.musicdatabase.db.AudioLibraryDAOImpl;
import com.netcracker.training.musicdatabase.model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.math.BigInteger;

/**
 * Created by MuxaHoid on 5/11/2015.
 */
@Stateless
public class RegistrationServiceImpl implements RegistrationService{
    public boolean register(String username, String password) {
        AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
        User newUser = dao.getUserByName(username);
        if(newUser == null) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = null;
            byte[] hash = null;
            try {
                f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                hash = f.generateSecret(spec).getEncoded();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            newUser = new User();
            newUser.setName(username);
            newUser.setHash(new BigInteger(1, hash).toString(16));
            newUser.setSalt(new BigInteger(1, salt).toString(16));
            dao.addUser(newUser);
            return true;
        }
        else return false;
    }

    public boolean authenticate(String username, String password) {
        AudioLibraryDAOImpl dao = new AudioLibraryDAOImpl();
        User user = dao.getUserByName(username);
        if(user!=null){
            SecureRandom random = new SecureRandom();
            byte[] salt = new BigInteger(user.getSalt(),16).toByteArray();
            String salt1 = new BigInteger(1, salt).toString(16);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = null;
            byte[] checkedHash = null;
            String check = null;
            try {
                f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                checkedHash = f.generateSecret(spec).getEncoded();
                check = new BigInteger(1, checkedHash).toString(16).toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            return check.equals(user.getHash());
        }
        else return false;
    }
}
