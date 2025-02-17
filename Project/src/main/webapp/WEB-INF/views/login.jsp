<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
</head>
<body>

<h2>Login</h2>
<form action="<%= request.getContextPath() %>/auth/login" method="post">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <label for="username">Username:</label>
    <input id="username" name="username" type="text" required placeholder="Enter your username">

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required minlength="8" autocomplete="off" placeholder="Enter your password">


    <div style="display: flex; gap: 10px;">
        <button type="submit" style="background: #007bff; color: white;">Sign In</button>
        <a href="<%= request.getContextPath() %>/auth/register">
            <button type="button" style="background: #28a745; color: white;">Sign Up</button>
        </a>
    </div>
</form>

</body>
</html>
