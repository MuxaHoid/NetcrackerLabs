<%@ page import="com.netcracker.training.musicdatabase.service.Service" %>
<%@ page import="com.netcracker.training.musicdatabase.service.ServiceImpl" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Album" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Track" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Artist" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%--
  Created by IntelliJ IDEA.
  User: MuxaHoid
  Date: 4/26/2015
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h1>Parameters</h1>
<form action="/parctrl" method="post">
    <%
        Context context = new InitialContext();
        Service service = (Service) context.lookup(Service.class.getName());
        String type = request.getParameter("type");
        if (type.equals("album")) {
    %>
    <input type="hidden"
           name="id" <%if(request.getParameterMap().containsKey("id")){ Album a = service.getAlbumByID(Long.parseLong(request.getParameter("id")));%>
           value="<%=request.getParameter("id")%>">
    Title: <input type="text" name="Title" value="<%=a.getTitle()%>">
    <br>
    <input type="submit" name="album" value="Edit">
    <%} else {%>
    value="">
    Title: <input type="text" name="Title">
    <br>
    <input type="submit" name="album" value="Add">
    <%}%>
    <%
    } else if (type.equals("track")) {
    %>
    <input type="hidden" name="id" <%if(request.getParameterMap().containsKey("id")){ Track t = service.getTrackByID(Long.parseLong(request.getParameter("id")));%>
           value="<%=request.getParameter("id")%>">
    Title: <input type="text" name="Title" value="<%=t.getTitle()%>">
    <br>
    Albums: <input type="text" name="Albums" value="<%Set<Album> albums = t.getAlbums();
                for (Album a : albums) {
                out.println(a.getTitle()+";");
                }%>">
    <br>
    Artist: <input type="text" name="Artist" value="<%=t.getArtist().getName()%>">
    <br>
    Genre: <input type="text" name="Genre" value="<%=t.getGenre().getName()%>">
    <br>
    Length: <input type="text" name="Length" value="<%=t.getLength()%>">
    <br>
    <input type="submit" name="track" value="Edit">
        <%}else{%>
           value="">
    Title: <input type="text" name="Title">
    <br>
    Albums: <input type="text" name="Albums">
    <br>
    Artist: <input type="text" name="Artist">
    <br>
    Genre: <input type="text" name="Genre">
    <br>
    Length: <input type="text" name="Length">
    <br>
    <input type="submit" name="track" value="Add">
        <%}%>
    <%
    } else if (type.equals("artist")) {
    %>
    <input type="hidden" name="id" <%if(request.getParameterMap().containsKey("id")){Artist artist = service.getArtistByID(Long.parseLong(request.getParameter("id")));%>
           value="<%=request.getParameter("id")%>">
    Name: <input type="text" name = "Name" value="<%=artist.getName()%>">
    <br>
    <input type="submit" name="artist" value="Edit">
        <%}else{%>
           value="">
    Name: <input type="text" name="Name">
    <br>
    <input type="submit" name="artist" value="Add">
    <%}%>
    <%
        }
    %>
</form>
</body>
</html>
