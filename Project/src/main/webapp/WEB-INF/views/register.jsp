<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <!-- Corrected CSS path to resources/static -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>

<div class="login-container">
    <h2>Register</h2>
    <!-- Registration Form -->
    <form action="${pageContext.request.contextPath}/auth/register" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required />

        <!-- CSRF Token (Spring Security) -->
        <<input type="hidden" name="_csrf" value="${_csrf.token}" />

        <!-- Register Button -->
        <button type="submit" class="login-btn">Register</button>

        <!-- Login Link -->
        <a class="hyperlink" href="${pageContext.request.contextPath}/auth/login">Already have an account? Login now!</a>
    </form>
</div>

</body>
</html>