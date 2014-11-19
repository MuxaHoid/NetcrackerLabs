package com.netckracker.training.musicdatabase.view;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by Артем on 18.11.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Request {

    public Request(){

    }


    @XmlElement
    private Track[] tracks;
    @XmlAttribute
    private String type;



    public void writeXML(String operation, String album, String artist, String genre, String title, int time) {
        Request response = new Request();
        response.type = operation;
        Track track = new Track();
        track.setAlbum(album);
        track.setArtist(artist);
        track.setGenre(genre);
        track.setTitle(title);
        track.setTime(time);
        response.tracks = new Track[]{track};
        try {

            JAXBContext jc = JAXBContext.newInstance(Request.class);
            Marshaller m = jc.createMarshaller();
            OutputStream os = new FileOutputStream("test.xml");
            m.marshal(response, os);
            os.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }





}