<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Badges</title>
</head>
<body>
<h1>Welcome, ${user.username}!</h1>

<!-- Display error messages -->
<c:if test="${not empty error}">
    <div style="color: red;">
        Error: ${error}
    </div>
</c:if>

<h2>Your Badges</h2>
<ul>
    <c:forEach items="${badges}" var="badge">
        <li>${badge.name}</li>
    </c:forEach>
</ul>

<h2>Completed Courses</h2>
<ul>
    <c:forEach items="${completedCourses}" var="course">
        <li>${course.course.title}</li>
    </c:forEach>
</ul>

<h2>Complete a Course</h2>
<form action="/complete-course" method="post">
    <input type="hidden" name="userId" value="${user.id}">
    <label for="courseId">Course ID:</label>
    <input type="text" id="courseId" name="courseId" required>
    <button type="submit">Complete Course</button>
</form>
</body>
</html>