package com.netckracker.training.musicdatabase.controller;

import com.sun.xml.internal.ws.util.NoCloseInputStream;

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
        Locale.setDefault(Locale.ENGLISH);
        try{
            ServerSocket ss = new ServerSocket(2280);
            Socket client = ss.accept();
            final BufferedReader socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            final StringReader dataReader = new StringReader(socketReader.readLine());
            Request request;
            while(true) {
                System.out.println(client.getInetAddress()+ " connected");
                JAXBContext jc2 = JAXBContext.newInstance(Request.class);
                Unmarshaller um = jc2.createUnmarshaller();
                System.out.println("Getting request");
                request = (Request) um.unmarshal(dataReader);
                System.out.println("Request");
                JAXBContext jc1 = JAXBContext.newInstance(Request.class);
                Marshaller m = jc1.createMarshaller();
                System.out.println("Sending response");
                final StringWriter dataWriter = new StringWriter();
                m.marshal(Controller.getResult(request), dataWriter);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                out.write(dataWriter.toString());
                out.newLine();
                out.flush();
                System.out.println("Response sent");
            }
        }
        catch(Exception e){
            e.toString();
        }
        System.out.println("Server shutdown");
    }
}