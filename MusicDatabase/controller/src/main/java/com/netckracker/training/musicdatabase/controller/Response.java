package com.netckracker.training.musicdatabase.controller;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
@XmlRootElement
public class Response {
    @XmlElement
    Track[] tracks;
}
class Track{
    @XmlElement
    String id;
    @XmlElement
    String title;
    @XmlElement
    String artist;
    @XmlElement
    String album;
    @XmlElement
    String genre;
    @XmlElement
    String length;
}