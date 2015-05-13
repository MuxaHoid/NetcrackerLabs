package com.netcracker.training.musicdatabase.controller;

import com.netcracker.training.musicdatabase.model.Track;
import com.netcracker.training.musicdatabase.service.Service;
import com.netcracker.training.musicdatabase.service.ServiceImpl;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/8/2015.
 */
@WebServlet(name = "ParctrlServlet", urlPatterns = {"/parctrl"})
public class ParctrlServlet extends HttpServlet {
    @EJB
    private Service service;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameterMap().containsKey("track")) {
            if (request.getParameter("track").equals("Edit")) {
                service.editTrack(request.getParameter("id"),
                        request.getParameter("Title"),
                        request.getParameter("Albums"),
                        request.getParameter("Artist"),
                        request.getParameter("Genre"),
                        request.getParameter("Length"));
            } else if (request.getParameter("track").equals("Add")) {
                service.addTrack(request.getParameter("Title"),
                        request.getParameter("Albums"),
                        request.getParameter("Artist"),
                        request.getParameter("Genre"),
                        request.getParameter("Length"));
            }
            response.sendRedirect("track.jsp");
        } else if (request.getParameterMap().containsKey("album")) {
            if (request.getParameter("album").equals("Edit")) {
                service.editAlbum(request.getParameter("id"),
                        request.getParameter("Title"));
            } else if (request.getParameter("album").equals("Add")) {
                service.addAlbum(request.getParameter("Title"));
            }
            response.sendRedirect("album.jsp");
        } else if (request.getParameterMap().containsKey("artist")) {
            if (request.getParameter("artist").equals("Edit")) {
                service.editArtist(request.getParameter("id"),
                        request.getParameter("Name"));
            } else if (request.getParameter("artist").equals("Add")) {
                service.addArtist(request.getParameter("Name"));
            }
            response.sendRedirect("artist.jsp");
        }
    }
}
