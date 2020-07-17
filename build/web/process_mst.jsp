<%-- 
    Document   : process_mst
    Created on : 2 Mar, 2015, 3:54:29 PM
    Author     : user
--%>
<%@page import="java.sql.Statement"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="algo.Retrieve"%>
<%@page import="java.util.HashMap"%>
<%@page import="algo.ReadFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<%@include file="flash.jsp" %>
<!DOCTYPE html>
<div class="container">
    <%
        int count;

        HashMap<String, Double> selected_features = new HashMap<String, Double>();

        selected_features = (HashMap<String, Double>) session.getAttribute("selected_features");
        session.setAttribute("selected_features", selected_features);

        long startTime = System.currentTimeMillis();
        Retrieve.setTime(startTime);
        double[][] adj_matrix = new double[][]{};
        ArrayList<String> features = new ArrayList<String>();
        HashMap<String, Double> hs = new HashMap<String, Double>();
        features = (ArrayList<String>) session.getAttribute("s_features");
        adj_matrix = (double[][]) session.getAttribute("ad_matrix");
        String type = request.getParameter("type");
        session.setAttribute("type", type);
        System.out.println("type:-> " + type);
        count = features.size();
        System.out.println("Count:-->" + count);
        System.out.println(type);
        System.out.println("Selected Features");
        for (int i = 0; i < features.size(); i++) {
            System.out.println(features.get(i));
        }
        hs = ReadFile.select_algo(adj_matrix, features.size(), type);
        session.setAttribute("mst", hs);
        int j = 1;
        System.out.println("Process MST");%>
    <table class="table table-hover table-border">
        <thead>
            <tr>
                <td>SR.NO</td>
                <td>To</td>
                <td>From</td>
                <td>Value</td>
            </tr>
        </thead>
        <tbody>

            <%
                for (Map.Entry<String, Double> entry : hs.entrySet()) {

                    String[] s = entry.getKey().split("-");%>
            <tr>
                <td><%=j%></td>
                <td><%=s[0]%></td>
                <td><%=s[1]%></td>
                <td><%=entry.getValue()%></td>
            </tr>
            <%
                    j++;
                }
                Thread.sleep(10);
                System.out.println("Start time:" + startTime);
                long endTime = System.currentTimeMillis();
                System.out.println("end time:" + endTime);
                long executionTime = endTime - startTime;
//                executionTime = executionTime;
                System.out.println("execution time: " + executionTime);
                String sql_time = "INSERT INTO analysis (algo,time) VALUES('" + type + "','" + executionTime + "')";
                Statement st = db.dbconn.connect();
                st.executeUpdate(sql_time);
                st.close();

            %>
        </tbody>
    </table>
    <%
%>
</div>
<%@include file="footer.jsp" %>
