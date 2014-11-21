package com.netckracker.training.musicdatabase.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MuxaHoid on 20.11.2014.
 */
public class ParallelServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(2280);

            while(true) {
                Work w = new Work(ss.accept());
                Thread t = new Thread(w) ;
                t.start();
            }
        }
        catch(Exception e){
            e.toString();
        }

    }
}
class Work implements Runnable
{
    Socket socket;

    Work(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run () {
        try{
            InputStream clientin = socket.getInputStream();
            OutputStream clientout = socket.getOutputStream();
            while(true){
                //do some work
            }
        }
        catch(Exception e){
            e.toString();
        }
    }
}