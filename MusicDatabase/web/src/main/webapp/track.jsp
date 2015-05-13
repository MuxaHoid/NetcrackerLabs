<%@ page import="com.netcracker.training.musicdatabase.service.Service" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Track" %>
<%@ page import="com.netcracker.training.musicdatabase.service.ServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Album" %>
<%@ page import="java.util.Set" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
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
<head>
    <title>Tracks</title>

</head>
<body>
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
        </tr>
        </thead>
        <tbody>
            <%
					 Context context = new InitialContext();
                     Service service = (Service) context.lookup(Service.class.getName());
					 List<Track> list;
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
        </tr>
            <%}%>
        <tbody>
    </table>
</form>
</body>
</html>
