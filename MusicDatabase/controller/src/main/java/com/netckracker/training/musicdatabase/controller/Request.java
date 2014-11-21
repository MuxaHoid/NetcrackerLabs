package com.netckracker.training.musicdatabase.controller;

import javax.xml.bind.annotation.*;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Request {
    @XmlAttribute
    String type;
    @XmlElement
    Track tracks;
}
