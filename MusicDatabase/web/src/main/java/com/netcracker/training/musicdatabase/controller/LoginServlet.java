package com.netcracker.training.musicdatabase.controller;

import com.netcracker.training.musicdatabase.service.RegistrationService;
import com.netcracker.training.musicdatabase.service.RegistrationServiceImpl;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/12/2015.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
    @EJB
    private RegistrationService registrationService;
    //RegistrationService registrationService = new RegistrationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("username");
        boolean result;
        result = registrationService.authenticate(request.getParameter("username"), request.getParameter("password"));
        if (result) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",user);
            response.sendRedirect("library.jsp");
        }
        else response.sendRedirect("error.jsp");
    }

}
