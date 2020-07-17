<%
    session.setAttribute("username", null);
    session.setAttribute("UserType", null);
    session.setAttribute("security",null);
    session.setAttribute("file", null);
//String user=session.getAttribute("User").toString();
//out.println(user);

    session.setAttribute("flash_message", "logged out successfully !!!");
//    session.setAttribute("flash_message", user);
    session.setAttribute("flash_type", "info");


    session.invalidate();
    response.sendRedirect("login.jsp");



%>

