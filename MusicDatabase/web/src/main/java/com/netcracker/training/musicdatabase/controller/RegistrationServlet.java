package com.netcracker.training.musicdatabase.controller;

import com.netcracker.training.musicdatabase.model.User;
import com.netcracker.training.musicdatabase.service.RegistrationService;
import com.netcracker.training.musicdatabase.service.RegistrationServiceImpl;
import com.netcracker.training.musicdatabase.service.Service;
import com.netcracker.training.musicdatabase.service.ServiceImpl;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/11/2015.
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {
    @EJB
    private RegistrationService registrationService;
    //RegistrationService registrationService = new RegistrationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("username");
        boolean result;
        result = registrationService.register(user, request.getParameter("password"));
        if (result) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",user);
            response.sendRedirect("library.jsp");
        }
        else response.sendRedirect("error.jsp");
    }
}
