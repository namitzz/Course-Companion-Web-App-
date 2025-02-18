<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <script>
        // Redirect users who are not logged in
        if (!localStorage.getItem("user")) {
            window.location.href = "login.jsp";
        }

        function logout() {
            localStorage.removeItem("user");
            window.location.href = "login.jsp";
        }
    </script>
</head>
<body>
<h2>Welcome to the Dashboard</h2>
<p>Hello, <script>document.write(localStorage.getItem("user"));</script>!</p>
<button onclick="logout()">Logout</button>
</body>
</html>
