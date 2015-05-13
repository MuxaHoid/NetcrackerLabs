<%@ page import="com.netcracker.training.musicdatabase.service.Service" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Track" %>
<%@ page import="com.netcracker.training.musicdatabase.service.ServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Album" %>
<%@ page import="java.util.Set" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="com.netcracker.training.musicdatabase.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: MuxaHoid
  Date: 4/26/2015
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<html>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<head>
    <title>Tracks</title>
</head>
<body>
<script>
    function rate(element) {
        var user = $('#user').val();
        var track = $(element).attr('name');
        var vote = $('input:radio[name=' + track + ']:checked').val();
        $.post("/rate", { vote : vote , user : user ,track : track } );
    }
</script>
<a href="library.jsp">Return to main page</a>
<br>
<form action="/track" method="post">
    <table>
        <input type="submit" name="action" value="add">
        <input type="submit" name="action" value="edit">
        <input type="submit" name="action" value="delete">
        <thead>
        <tr>
            <th></th>
            <th>Track ID</th>
            <th>Title</th>
            <th>Artist</th>
            <th>Albums</th>
            <th>Genre</th>
            <th>Length</th>
            <th>Your rating</th>
        </tr>
        </thead>
        <tbody>
            <%
					 Context context = new InitialContext();
                     Service service = (Service) context.lookup(Service.class.getName());
					 List<Track> list;
					 User currentUser = service.getUserByName(session.getAttribute("currentSessionUser").toString());
					 if(request.getParameterMap().containsKey("album")){
					 list = service.getTracksByAlbum(Long.parseLong(request.getParameter("album")));
					 }else if(request.getParameterMap().containsKey("artist")){
					 list = service.getTracksByArtist(Long.parseLong(request.getParameter("artist")));
					 }else{
					    list = service.getTracks();
					 }
					 for (Track t : list) {
				 %>
        <tr>
            <td><input type=checkbox name="track" value="<%=t.getId()%>"></td>
            <td><a href="parctrl.jsp?type=track&id=<%=t.getId()%>"><%=t.getId()%>
            </a></td>
            <td><a href="parctrl.jsp?type=track&id=<%=t.getId()%>"><%=t.getTitle()%>
            </a></td>
            <td><%=t.getArtist().getName()%>
            </td>
            <td><%
                Set<Album> albums = t.getAlbums();
                for (Album a : albums) {
                    out.println(a.getTitle() + "\n");
                }
            %>
            </td>
            <td><%=t.getGenre().getName()%>
            </td>
            <td><%=t.getLength()%>
            </td>
            <td>
                <form name="<%=t.getId()%>" id="<%=t.getId()%>">
                    <input type =hidden value="<%=currentUser.getName()%>" id = "user">
                    no:
                    <input type="radio" value="0" name = "<%=t.getId()%>"
                        <%if(!currentUser.getVotes().containsKey(t)){%>
                           checked="checked"
                    <%}%>
                           onclick="rate(this)">
                    <%for (int i = 1; i <= 5; i++) {
                    out.print(i+":");%>
                    <input type="radio" value="<%=i%>"
                        <%if(currentUser.getVotes().containsKey(t)&&currentUser.getVotes().get(t)==i){%>
                           checked="checked"
                        <%}%>
                           name="<%=t.getId()%>" onclick="rate(this)">
                    <%}%>
                </form>
            </td>
        </tr>
            <%}%>
        <tbody>
    </table>
</form>
</body>
</html>
