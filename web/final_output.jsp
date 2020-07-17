<%-- 
    Document   : final_output
    Created on : 17 Mar, 2015, 11:45:48 AM
    Author     : user
--%>

<%@page import="java.sql.Statement"%>
<%@page import="java.util.Collections"%>
<%@page import="com.login.Sout"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="java.lang.Integer"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.lang.String"%>
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
        ReadFile.process_one();
//        ArrayList<String> features = new ArrayList<String>();
//        ArrayList<String> subset_features = new ArrayList<String>();
//        HashMap<String, Double> selected_features = new HashMap<String, Double>();
//
//        HashMap<String, Double> features_correlation = new HashMap<String, Double>();
//        selected_features = (HashMap<String, Double>) session.getAttribute("selected_features");
//        features_correlation = (HashMap<String, Double>) Retrieve.getFeatureCorrelation();
//        int count = 0, endcount = 0, flag = 0;
//        for (Map.Entry<String, Double> entry : selected_features.entrySet()) {
//            features.add(entry.getKey());
//        }
//        for (Map.Entry<String, Double> entry : features_correlation.entrySet()) {
//            String[] key = entry.getKey().split(",");
//            double value = entry.getValue();
//            if (value >= selected_features.get(key[1]));
//            {
//                features.remove(key[1]);
//            }
//        }
%>

    <div class="container">
        <h1>Subset of Feature Selected</h1>
        <!--<h3><features ></h3>-->
        <div class="well"><h2><b><%=ReadFile.final_feature_suset_list%></b></h2></div>
    </div>
    <% long fullEndTime = System.currentTimeMillis();
        long fullStratTime = (Long) session.getAttribute("fullStartTime");
        long time = fullEndTime - fullStratTime;
        Statement st = db.dbconnection.connect();
        String sql = "INSERT INTO final_analysis(method,time) VALUES('FCBF','" + time + "')";
        int i = st.executeUpdate(sql);
        if (i == 0) {
            System.out.println("Not Inserted");
        } else {
            System.out.println("Inserted");
        }
    %>
    <%@include file="footer.jsp" %>
