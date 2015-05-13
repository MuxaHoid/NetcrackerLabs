package com.netcracker.training.musicdatabase.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MuxaHoid on 5/11/2015.
 */
@Entity
@Table(name="Client")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;
    @Column(name="user_name")
    private String name;
    @Column(name="user_salt")
    private String salt;
    @Column(name="user_hash")
    private String hash;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "votes", joinColumns = {
            @JoinColumn(name = "user_id") })
    @MapKeyJoinColumn(name="track_id",referencedColumnName = "track_id")
    @Column(name="vote")
    private Map<Track,Integer> votes = new HashMap<Track, Integer>();

    public User(String name, String salt, String hash, Map<Track, Integer> votes) {
        this.name = name;
        this.salt = salt;
        this.hash = hash;
        this.votes = votes;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Map<Track, Integer> getVotes() {
        return votes;
    }

    public void setVotes(Map<Track, Integer> vote) {
        this.votes = vote;
    }
}
