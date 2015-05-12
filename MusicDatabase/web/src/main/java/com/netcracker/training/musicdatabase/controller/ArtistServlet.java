package com.netcracker.training.musicdatabase.controller;

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
 * Created by MuxaHoid on 5/10/2015.
 */
@WebServlet(name = "ArtistServlet", urlPatterns = {"/artist"})
public class ArtistServlet extends HttpServlet{
    @EJB
    private Service service;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            response.sendRedirect("parctrl.jsp?type=artist");
        } else if (action.equals("edit")) {
            if (request.getParameterMap().containsKey("artist"))
                response.sendRedirect("parctrl.jsp?type=artist&id=" + request.getParameter("artist"));
            else response.sendRedirect(request.getHeader("referer"));
        } else if (action.equals("delete")) {
            String[] params = request.getParameter("artist").split(";");
            if (params.length > 0)
                service.removeArtist(Long.parseLong(params[0]));
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
