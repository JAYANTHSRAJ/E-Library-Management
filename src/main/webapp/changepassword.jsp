<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password</title>
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
        margin: 50px auto;
        width: 400px;
        padding: 25px;
        background: rgba(255, 255, 255, 0.9);
        border-radius: 12px;
        box-shadow: 0px 4px 15px rgba(0,0,0,0.3);
        font-family: Arial, sans-serif;
    }
    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }
    .form-container label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
        color: #444;
    }
    .form-container input {
        width: 100%;
        padding: 10px;
        margin-bottom: 18px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
    }
    .btn {
        border: 2px solid grey;
        border-radius: 29px;
        margin: 10px auto;
        width: 150px;
        font-size: 15px;
        padding: 10px;
        background: none;
        font-family: 'Ubuntu', sans-serif;
        outline: none;
        display: block;
    }
    .btn:hover {
        cursor: pointer;
        background-color: burlywood;
    }
    .message {
        text-align: center;
        margin-top: 20px;
        font-size: 16px;
    }
</style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" 
      style="background-repeat: no-repeat; background-size: cover;">

<header>
    <div class="logo">
        <img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px">
    </div>
    <div class="text">
        <h1> E-Library </h1> 
        <span>
            <span style="padding-right: 15px;">
                <a href="<%= request.getContextPath() %>/student_operations.html" style="font-weight: normal; text-decoration: none; color: inherit;">Home</a>
            </span>
            <span style="padding-right: 15px;">
                <a href="<%= request.getContextPath() %>/about.html" style="font-weight: normal; text-decoration: none; color: inherit;">About</a>
            </span>
            <span>
                <a href="<%= request.getContextPath() %>/contactus.html" style="font-weight: normal; text-decoration: none; color: inherit;">Contact Us</a>
            </span>
        </span>
    </div>
</header>

<div class="form-container">
    <h2>Change Password</h2>
    <form action="StudentController/changepassword" method="post">
        <label>Old Password:</label>
        <input type="password" name="old_password" required />

        <label>New Password:</label>
        <input type="password" name="new_password" required />

        <label>Confirm New Password:</label>
        <input type="password" name="confirm_password" required />

        <button type="submit" class="btn">Change Password</button>
    </form>

    <!-- ✅ Status Messages -->
    <c:choose>
        <c:when test="${status eq 'success'}">
            <p class="message" style="color:green;">
                ${message}
            </p>
        </c:when>
        <c:when test="${status eq 'failure'}">
            <p class="message" style="color:red;">
                ${errorMessage}
            </p>
        </c:when>
    </c:choose>
</div>

</body>
</html>
