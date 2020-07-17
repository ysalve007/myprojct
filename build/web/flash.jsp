<%
    //checking if flash message is set
    String flash_message = (String) session.getAttribute("flash_message");
    if (flash_message != null) {
        String flash_type = (String) session.getAttribute("flash_type");
        if (flash_type == null) {
            flash_type = "success";
        }
        session.removeAttribute("flash_message");
        session.removeAttribute("flash_type");
%>
<div class="container">
    <div class="alert alert-<%=flash_type%>">
        <%=flash_message%>
        <button class="close" data-dismiss="alert">x</button>
    </div>
</div>
<%
    }
%>