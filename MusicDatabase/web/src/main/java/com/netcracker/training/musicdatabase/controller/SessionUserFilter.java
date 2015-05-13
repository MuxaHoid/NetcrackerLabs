package com.netcracker.training.musicdatabase.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by MuxaHoid on 5/12/2015.
 */
@WebFilter(filterName = "SessionUserFilter", urlPatterns = {"/library.jsp","/album.jsp","/artist.jsp","/track.jsp","/parctrl.jsp"})
public class SessionUserFilter implements Filter{
    private String contextPath;

    public void init(FilterConfig fc) throws ServletException {
        contextPath = fc.getServletContext().getContextPath();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getSession().getAttribute("currentSessionUser") == null) {
            res.sendRedirect(contextPath+"index.jsp");
        } else {
            fc.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
