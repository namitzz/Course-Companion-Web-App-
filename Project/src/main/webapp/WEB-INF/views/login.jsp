<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/auth/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <div class="button-group">
            <button type="submit" class="btn-signin">Sign In</button>
            <a href="${pageContext.request.contextPath}/auth/register" class="btn-signup">Sign Up</a>
        </div>
    </form>
</div>
</body>
</html>