<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/auth/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <button type="submit" class="btn-signin">Sign In</button>
        <a href="${pageContext.request.contextPath}/auth/register" class="btn-signup">Sign Up</a>
    </form>
</div>
</body>
</html>