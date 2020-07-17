<%-- 
    Document   : relevant_features
    Created on : 2 Mar, 2015, 12:51:46 PM
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
<div class="container">
    <h3 style="text-align: center">Feature Classification After T-Relevance Calculation</h3>
    <%
//    HttpSession session = request.getSession();
        double t_relavance;
        HashMap<String, Double> selected_features = new HashMap<String, Double>();
        HashMap<String, Double> unselected_features = new HashMap<String, Double>();
//        session.setAttribute("selected_features", selected_features);

        ArrayList<String> s_features = new ArrayList<String>();
//        double[][] ad_matrix = new double[][]{};
        ReadFile.process();

        selected_features = Retrieve.getSelectedFeatures();
        session.setAttribute("selected_features", selected_features);
        unselected_features = Retrieve.getUnSelectedFeatures();
        t_relavance = Retrieve.getThreshold();

        String sql = "TRUNCATE analysis";
        Statement st = db.dbconn.connect();
        st.executeUpdate(sql);
        st.close();


    %>
    <div class ="container">
        <div class="span4"></div>
        <div class="span4">
            <h4>Selected Features (Relevant Features)</h4>
            <table class="table table-border" style="border: 1px solid">
                <thead>
                    <tr>
                        <th><b>Feature Name</b></th>
                        <th><b>T-Relevance</b></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map.Entry<String, Double> entry : selected_features.entrySet()) {
                            s_features.add(entry.getKey());
                    %>
                    <tr>
                        <td><%=entry.getKey()%></td>
                        <td><%=entry.getValue()%></td>
                    </tr>
                    <%  }
                        session.setAttribute("s_features", s_features);
//                        session.setAttribute("ad_matrix", ad_matrix);
                    %>
                </tbody>
            </table>
        </div>
        <div class="span4"></div>   
    </div>
    <br>
    <div class="container">
        <div class="span4"></div>
        <div class="span4">
            <h4>Unselected Features (Irrelevant Features)</h4>
            <table class="table table-border" style="border: 1px solid">
                <thead>
                    <tr>
                        <th><b>Feature Name</b></th>
                        <th><b>T-Relevance</b></th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map.Entry<String, Double> entry : unselected_features.entrySet()) {
//                        s_features.add(entry.getKey());
                    %>
                    <tr>
                        <td><%=entry.getKey()%></td>
                        <td><%=entry.getValue()%></td>

                    </tr>
                    <%  }
                        session.setAttribute("s_features", s_features);
//                        session.setAttribute("ad_matrix", ad_matrix);
                    %>
                </tbody>
            </table>
        </div> 
        <div class="span4"></div>
    </div>

</div>
<%@include file="footer.jsp" %>
