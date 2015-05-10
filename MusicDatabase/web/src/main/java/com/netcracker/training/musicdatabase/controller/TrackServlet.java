package com.netcracker.training.musicdatabase.controller;

import com.netcracker.training.musicdatabase.service.Service;
import com.netcracker.training.musicdatabase.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/6/2015.
 */
@WebServlet(name = "TrackServlet", urlPatterns = {"/track"})
public class TrackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            response.sendRedirect("parctrl.jsp?type=track");
        } else if (action.equals("edit")) {
            if (request.getParameterMap().containsKey("track"))
                response.sendRedirect("parctrl.jsp?type=track&id=" + request.getParameter("track"));
            else response.sendRedirect(request.getHeader("referer"));
        } else if (action.equals("delete")) {
            Service service = new ServiceImpl();
            String[] params = request.getParameter("track").split(";");
            if (params.length > 0)
                service.removeTrack(Long.parseLong(params[0]));
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
