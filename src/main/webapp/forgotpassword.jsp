<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/student_style.css">
<style>
    header {
        background-color: transparent; 
        padding: 10px 0;
        color: #000;
    }
    .logo {
        display: inline-block;
    }
    .text {
        display: inline-block;
        margin-left: 20px;
    }
    .dummies {
        float: right;
    }
    .dummies ul {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .dummies li {
        display: inline;
        margin-left: 20px;
    }
    .dummies a {
        text-decoration: none;
        color: #000;
    }
    .form-container {
        width: 400px;
        margin: 100px auto;
        padding: 30px;
        border: 1px solid #ccc;
        border-radius: 15px;
        background: rgba(255, 255, 255, 0.9);
        box-shadow: 0px 0px 10px rgba(0,0,0,0.3);
    }
    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .form-container input[type="text"],
    .form-container input[type="email"],
    .form-container input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border-radius: 8px;
        border: 1px solid #ccc;
    }
    .form-container input[type="submit"] {
        background-color: burlywood;
        border: none;
        cursor: pointer;
        font-weight: bold;
    }
    .form-container input[type="submit"]:hover {
        background-color: peru;
    }
    .message {
        text-align: center;
        margin-top: 15px;
    }
</style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" style="background-repeat: no-repeat; background-size: cover;">
    <header>
        <div class="logo"><img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px"></div>
        <div class="text">
            <h1>E-Library</h1>
            <span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/student_operations.html">Home</a></span>
                <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>/about.html">About</a></span>
                <span><a href="<%= request.getContextPath() %>/contactus.html">Contact Us</a></span>
            </span>
        </div>
    </header>

    <div class="form-container">
        <h2>Forgot Password</h2>
        <form action="<%=request.getContextPath()%>/StudentController/forgotpassword" method="post">
            <input type="text" name="student_id" placeholder="Enter Student ID..." required>
            <input type="text" name="student_email" placeholder="Enter Registered Email..." required>
            <input type="submit" value="Submit">
        </form>

        <div class="message">
            <c:if test="${status eq 'success'}">
                <p style="color: green;">${message}</p>
            </c:if>
            <c:if test="${status eq 'failure'}">
                <p style="color: red;">${errorMessage}</p>
            </c:if>
        </div>
    </div>
</body>
</html>
