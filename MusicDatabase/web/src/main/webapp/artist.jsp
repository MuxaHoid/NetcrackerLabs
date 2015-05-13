<%@ page import="com.netcracker.training.musicdatabase.service.Service" %>
<%@ page import="com.netcracker.training.musicdatabase.model.Artist" %>
<%@ page import="com.netcracker.training.musicdatabase.service.ServiceImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.Context" %>
<%--
  Created by IntelliJ IDEA.
  User: MuxaHoid
  Date: 4/26/2015
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Artists</title>
</head>
<body>
<a href="library.jsp">Return to main page</a>

<form action="/artist" method="post">
    <input type="submit" name="action" value="add">
    <input type="submit" name="action" value="edit">
    <input type="submit" name="action" value="delete">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>Artist ID</th>
            <th>Artist Name</th>
        </tr>
        </thead>
        <tbody>
            <%
					 Context context = new InitialContext();
                     Service service = (Service) context.lookup(Service.class.getName());
					 List<Artist> list = service.getArtists();
					 for (Artist a : list) {
				 %>
        <tr>
            <td><input type=checkbox name="artist" value="<%=a.getId()%>"></td>
            <td><a href="track.jsp?artist=<%=a.getId()%>"><%=a.getId()%>
            </a></td>
            <td><a href="track.jsp?artist=<%=a.getId()%>"><%=a.getName()%>
            </a></td>
        </tr>
            <%}%>
        <tbody>
    </table>
</form>
</body>
</html>
