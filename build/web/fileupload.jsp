<%@page import="java.sql.Statement"%>
<%@include file="header.jsp" %>
<%@include file="manage_nav.jsp" %>
<%@include file="flash.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload</title>
        <script>
            function check() {
                var uploadpath = $('#file_upload').val();
                var fileExtension = uploadpath.substring(uploadpath.lastIndexOf(".") + 1, uploadpath.length);
                if ($('#file_upload').val().length === 0) {
                    return false;
                }
                if (fileExtension === "txt" || fileExtension === "text") {
                    return true;
                }
                else {
                    alert("Select .txt Files only");
                    return false;
                }
            }

            function check() {
                var uploadpath = $('#file_upload1').val();
                var fileExtension = uploadpath.substring(uploadpath.lastIndexOf(".") + 1, uploadpath.length);
                if ($('#file_upload1').val().length === 0) {
                    return false;
                }
                if (fileExtension === "txt" || fileExtension === "text") {
                    return true;
                }
                else {
                    alert("Select images only");
                    return false;
                }
            }


        </script>
    </head>
    <body>
        <div class="container" style="background-size: cover">
            <div class="row background space20" style="background-image: url('img/login.jpg'); background-size: cover">
                <form action="fileUpload" method="post" class="form-signin" enctype="multipart/form-data" >
                    <h3 class="form-signin-heading">File Upload</h3>
                    <% String basepath = getServletContext().getRealPath("/");
                        String truncate_sql = "TRUNCATE  files";
                        System.out.println("truncate_sql: " + truncate_sql);
                        Statement st = db.dbconn.connect();
                        st.executeUpdate(truncate_sql);
//                        st.close();%>
                    Select a file to upload:
                    <table>
                        <tbody>
                            <tr>
                                <td>Feature List: </td>
                                <td><input id ="file_upload" type="file" class="span4" name="file"/></td>
                            </tr>
                            <tr>
                                <td>Feature Dataset: </td>
                                <td><input id ="file_upload1" type="file" class="span4" name="image"/></td>
                            </tr>
                            <tr>
                                <td>Class Feature: </td>
                                <td><input id ="text1" type="text" class="span2" name="text"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>
                    <input id="file_button" type="submit" class="btn btn-primary" value="Upload File" onclick=" return check()"/>
                </form>
            </div>
        </div>
    </body>
</html>
<%@include file="footer.jsp" %>