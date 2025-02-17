<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <!-- Corrected CSS path to resources/static -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>

<div class="login-container">
    <h2>Login</h2>
    <!-- Login Form -->
    <form action="<%= request.getContextPath() %>/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />

        <!-- Forgot Password Link -->
        <a class="hyperlink" href="/forgot-password">Forgot your password?</a>

        <!-- CSRF Token (Spring Security) -->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <!-- Login Button -->
        <button type="submit" class="login-btn">Login</button>

        <!-- Register Link -->
        <a class="hyperlink" href="/register">Don't have an account? Register now!</a>
    </form>
</div>

</body>
</html>
