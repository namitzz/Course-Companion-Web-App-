<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<div class="login-container">
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/auth/register" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required />
        <button type="submit" class="btn-signin">Register</button>
        <a href="${pageContext.request.contextPath}/auth/login" class="btn-signup">Back to Login</a>
    </form>
</div>
</body>
</html>