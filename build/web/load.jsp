<%-- 
    Document   : load
    Created on : 3 Jul, 2015, 5:00:34 PM
    Author     : user
--%>

<%@page import="algo.ReadFile"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            File file = new File("ThoraricSurgery.txt");
            System.out.println("" + request.getServletContext().getRealPath("/"));
            System.out.println("File: " + file);
            String path = request.getServletContext().getRealPath("/") + "dataset/" + file;
            ReadFile.truncate("disease_report");
            ReadFile.reading(path);
            System.out.println("Path:----> " + path);
        %>
        <div class="container"><h2>Data Set is Successfully Loaded into Database</h2></div>
    </body>
</html>
<%@include file="footer.jsp" %>
