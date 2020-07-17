<%-- 
    Document   : view_report
    Created on : 25 Jan, 2015, 6:49:40 AM
    Author     : user6
https://archive.ics.uci.edu/ml/datasets/Heart+Disease
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="algo.Retrieve"%>
<%@page import="algo.ReadFile"%>
<%@page import="db.dbconn"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<%@include file="flash.jsp" %>
<div class="container">
    <table class="table table-border table-hover">
        <thead>
            <tr>
                <th>SrNo.</th>
                <%
                    String class_feature = Retrieve.getClassFeature();
                    System.out.println("class_feature: " + class_feature);
                    ArrayList<String> features = new ArrayList<String>();
                    features = (ArrayList<String>) Retrieve.getFeatureFinalList();
                    System.out.println("features: " + features);
                    for (int i = 0; i < features.size(); i++) {
                %>
                <th><%=features.get(i)%></th>
                <%    }
                %>
                <th><%=class_feature%></th>
            </tr>
        </thead>
        <tbody>
            <%
                Statement st = db.dbconn.connect();
                String select_sql = "SELECT * FROM report";
                ResultSet rs = st.executeQuery(select_sql);
                while (rs.next()) {
            %>
            <tr>
                <td><%=rs.getString("sr_no")%></td>
                <% for (int i = 0; i < features.size(); i++) {
//                    System.out.println("features is "+features.get(i));
                        String s =(String)features.get(i);
                        s= s.trim();
        System.out.println("s: "+s);
%>
                <td><%=rs.getString(s)%></td>
                <%}%>
                <td><%=rs.getString(class_feature)%></td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>
<%@include file="footer.jsp" %>
