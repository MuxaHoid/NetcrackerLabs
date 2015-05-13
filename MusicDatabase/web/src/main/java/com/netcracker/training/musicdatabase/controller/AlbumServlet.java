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
@WebServlet(name = "AlbumServlet", urlPatterns = {"/album"})
public class AlbumServlet extends HttpServlet{
    @EJB
    private Service service;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("add")) {
            response.sendRedirect("parctrl.jsp?type=album");
        } else if (action.equals("edit")) {
            if (request.getParameterMap().containsKey("album"))
                response.sendRedirect("parctrl.jsp?type=album&id=" + request.getParameter("album"));
            else response.sendRedirect(request.getHeader("referer"));
        } else if (action.equals("delete")) {
            String[] params = request.getParameter("album").split(";");
            if (params.length > 0)
                service.removeAlbum(Long.parseLong(params[0]));
            response.sendRedirect(request.getHeader("referer"));
        }
    }
}
