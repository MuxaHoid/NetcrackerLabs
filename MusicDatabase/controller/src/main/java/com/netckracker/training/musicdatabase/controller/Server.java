package com.netckracker.training.musicdatabase.controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

/**
 * Created by MuxaHoid on 20.11.2014.
 */
public class Server {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Server started");
        Controller controller = new Controller();
        Locale.setDefault(Locale.ENGLISH);
        try {
            ServerSocket ss = new ServerSocket(2280);
            while (true) {
                Socket client = ss.accept();
                final BufferedReader socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                final StringReader dataReader = new StringReader(socketReader.readLine());
                System.out.println(client.getInetAddress() + " connected");
                Request request;
                JAXBContext jc2 = JAXBContext.newInstance(Request.class);
                Unmarshaller um = jc2.createUnmarshaller();
                System.out.println("Getting request");
                request = (Request) um.unmarshal(dataReader);
                System.out.println("Request " + request.type
                        +" "+ request.tracks.id
                        +" "+ request.tracks.title
                        +" "+ request.tracks.artist
                        +" "+ request.tracks.album
                        + " "+request.tracks.genre);
                JAXBContext jc1 = JAXBContext.newInstance(Response.class);
                Marshaller m = jc1.createMarshaller();
                System.out.println("Sending response");
                final StringWriter dataWriter = new StringWriter();
                Response resp = controller.getResult(request);
                m.marshal(resp, dataWriter);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                System.out.print(dataWriter.toString());
                out.write(dataWriter.toString());
                out.newLine();
                out.flush();
                System.out.println("Response sent");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Server shutdown");
    }
}