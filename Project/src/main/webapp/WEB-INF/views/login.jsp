<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        /* General Styling */
        body {
            font-family: 'Orbitron', sans-serif;
            background: radial-gradient(circle, #050505 30%, #000000 100%);
            color: #00ffc8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Form Container */
        .login-container {
            background: rgba(20, 20, 20, 0.9);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 255, 200, 0.5);
            text-align: center;
            width: 350px;
        }

        /* Headings */
        h2 {
            font-size: 24px;
            text-transform: uppercase;
            color: #00ffc8;
            text-shadow: 0 0 10px #00ffc8;
            animation: neonGlow 1.5s infinite alternate;
        }

        /* Labels */
        label {
            display: block;
            margin-top: 10px;
            font-size: 14px;
        }

        /* Input Fields */
        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: none;
            background: rgba(0, 255, 200, 0.1);
            color: #00ffc8;
            border-radius: 5px;
            outline: none;
            font-size: 16px;
            transition: all 0.3s ease-in-out;
        }

        /* Input Focus Effect */
        input:focus {
            box-shadow: 0 0 10px #00ffc8;
            background: rgba(0, 255, 200, 0.2);
        }

        /* Button Container */
        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        /* Buttons */
        button, .btn-signup {
            width: 48%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-transform: uppercase;
            transition: all 0.3s ease-in-out;
        }

        /* Sign In Button */
        .btn-signin {
            background: linear-gradient(90deg, #0066ff, #00bfff);
            color: white;
            box-shadow: 0 0 10px #00bfff;
        }

        /* Sign In Hover */
        .btn-signin:hover {
            background: linear-gradient(90deg, #0052cc, #0088ff);
            box-shadow: 0 0 15px #00bfff;
        }

        /* Register Button */
        .btn-signup {
            background: linear-gradient(90deg, #00ff66, #00cc44);
            color: white;
            text-decoration: none;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 0 10px #00ff66;
        }

        /* Register Hover */
        .btn-signup:hover {
            background: linear-gradient(90deg, #00cc44, #009933);
            box-shadow: 0 0 15px #00ff66;
        }

        /* Sci-Fi Glow Effect */
        @keyframes neonGlow {
            0% {
                text-shadow: 0 0 5px #00ffc8, 0 0 10px #00ffc8;
            }
            50% {
                text-shadow: 0 0 10px #00ffc8, 0 0 20px #00ffc8;
            }
            100% {
                text-shadow: 0 0 5px #00ffc8, 0 0 10px #00ffc8;
            }
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Please Sign In</h2>
    <form action="${pageContext.request.contextPath}/auth/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required />

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="button-group">
            <button type="submit" class="btn-signin">Sign In</button>
            <a href="${pageContext.request.contextPath}/auth/register" class="btn-signup">Register</a>
        </div>
    </form>
</div>
</body>
</html>