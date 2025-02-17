<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

</head>
<body>

<h2>Register</h2>
<form action="<%= request.getContextPath() %>/auth/register" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">

    <label for="username" aria-label="Username">Username:</label>
    <input id="username" name="username" type="text" required placeholder="Enter your username">
    <br>
    <label for="password" aria-label="Password">Password:</label>
    <input type="password" id="password" name="password" required minlength="8" autocomplete="off" placeholder="Enter your password">
    <br>
    <button type="submit">Register</button>
</form>


<p>Already have an account? <a href="<%= request.getContextPath() %>/login">Login</a></p>

</body>
</html>