<%@ page import="com.netcracker.training.musicdatabase.service.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="com.netcracker.training.musicdatabase.service.ServiceImpl" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Album" %>
<%--
  Created by IntelliJ IDEA.
  User: MuxaHoid
  Date: 4/26/2015
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Albums</title>
</head>
<body>
<a href="index.jsp">Return to main page</a>
<br>

<form action="/album" method="post">
    <input type="submit" name="action" value="add">
    <input type="submit" name="action" value="edit">
    <input type="submit" name="action" value="delete">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Album ID</th>
            <th>Album Title</th>
        </tr>
        </thead>
        <tbody>
            <%
					 Service service = new ServiceImpl();
					 List<Album> list = service.getAlbums();
					 for (Album a : list) {
				 %>
        <tr>
            <td><input type=checkbox name="album" value="<%=a.getId()%>"></td>
            <td><a href="track.jsp?album=<%=a.getId()%>"><%=a.getId()%>
            </a></td>
            <td><a href="track.jsp?album=<%=a.getId()%>"><%=a.getTitle()%>
            </a></td>
        </tr>
            <%}%>
        <tbody>
    </table>
</form>
</body>
</html>
