<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.User" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    <h2>Welcome <%= user.getEmail() %>!</h2>
    <p>You have successfully logged in with 2FA.</p>
    <% } else { %>
    <p>Please <a href="login.jsp">login</a> to access the dashboard.</p>
    <% } %>
</div>
</body>
</html>