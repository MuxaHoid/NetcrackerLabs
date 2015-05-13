package com.netcracker.training.musicdatabase.controller;

import com.netcracker.training.musicdatabase.model.Track;
import com.netcracker.training.musicdatabase.model.User;
import com.netcracker.training.musicdatabase.service.Service;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/13/2015.
 */
@WebServlet(name = "RateServlet", urlPatterns = {"/rate"})
public class RateServlet extends HttpServlet {
    @EJB
    private Service service;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = service.getUserByName(request.getParameter("user"));
        Track track = service.getTrackByID(Long.parseLong(request.getParameter("track")));
        int vote = Integer.parseInt(request.getParameter("vote"));
        boolean f = user.getVotes().containsKey(track);
        if(vote!=0)
        user.getVotes().put(track,vote);
        else user.getVotes().remove(track);
        service.updateUser(user);
    }
}
