<%-- 
    Document   : index
    Created on : 10 Jan, 2015, 11:08:22 AM
    Author     : user6
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<%@include file="flash.jsp" %>
<div class="container">
    <% long fullStartTime = System.currentTimeMillis();
        session.setAttribute("fullStartTime", fullStartTime);
    %>
    <h2>Welcome To Fast Clustering</h2>
    <br>
    <p>A Fast Clustering-Based Feature Subset Selection Algorithm  (CBFAST) is a fast filtering method.</p>
    <p>It finds relevant features as well as deals with  redundancy among it. </p>
    <p>It differs from other algorithms in terms of searching redundancy. </p>
    <p>This CBFAST algorithm uses the clustering-based method to choose features. </p>
    <p>It uses MST method to cluster features.</p>
</div>
<%@include file="footer.jsp" %>