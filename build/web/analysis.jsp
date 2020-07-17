<%-- 
    Document   : analysis
    Created on : 9 Jul, 2015, 5:48:27 PM
    Author     : eiosys
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.lang.Thread.State"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<%@include file="flash.jsp" %>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="/js/data.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>


<div class="well" id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<table id="datatable" class="hide">
    <thead>
        <tr>
            <th></th>
            <th>Prim</th>
            <th>Kruskal</th>
        </tr>
    </thead>
    <tbody>
        <tr><th> </th>
            <%
            String sql = "SELECT * FROM analysis";
            Statement st = db.dbconn.connect();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){
            %>
            <td><%=rs.getLong("time") %></td>
            <% }
            st.close();%>
        </tr>
    </tbody>
</table>



<script type="text/javascript">
        
    $(function() {
        $('#container').highcharts({
            data: {
                table: 'datatable'
            },
            chart: {
                type: 'column'
            },
            title: {
                text: 'Execution Time Analysis'
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: 'Executin Time (In Milliseconds)'
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>' + this.series.name + '</b><br/>' +
                        this.point.y + ' ' + 'ms';
                }
            }
        });
    });
</script>
<%@include file="footer.jsp" %>
