<%--
    Document   : index
    Created on : Mar 27, 2012, 6:46:41 AM
    Author     : zen9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to Point Based Loyalty Program Class</h1>

        <form name="loginform" action="/PLPS-war/LoginServlet">
            <div>
                <label>Name</label>
                <input type="text" name="username" value="" />
            </div>
            <div>
                <label>Password</label>
                <input type="password" name="password" value="" />
            </div>
            <div>
                <input type="submit" value="login" />
            </div>
        </form>

        <%
            String message = (String) request.getAttribute("failed");
            if (message != null) {
        %>

        <p><%=message%></p>

        <%
            }
        %>
    </body>
</html>
