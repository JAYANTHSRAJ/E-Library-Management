<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Registration Status</title>
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
        .btn {
            border: 2px solid grey;
            border-radius: 29px;
            margin: 7px auto;
            width: 86px;
            font-size: 15px;
            padding: 10px;
            background: none;
            font-family: 'Ubuntu', sans-serif;
            outline: none;
        }
        .btn:hover {
            cursor: pointer;
            background-color: burlywood;
        }
        .message {
            margin-top: 60px;
            text-align: center;
        }
        .success {
            color: white;
            background-color: green;
            padding: 10px 20px;
            border-radius: 10px;
            font-weight: bold;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.2);
        }
        .failed {
            color: white;
            background-color: crimson;
            padding: 10px 20px;
            border-radius: 10px;
            font-weight: bold;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.2);
        }
        .retry-link {
            display: inline-block;
            margin-top: 15px;
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .retry-link:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" style="background-repeat: no-repeat; background-size: cover;">
    <header>
        <div class="logo">
            <img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px">
        </div>
        <div class="text">
            <h1> e-Library </h1> 
                <span>
    <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>adminLogin.html" style="font-weight: normal; text-decoration: none; color: inherit;">Home</a></span>
    <span style="padding-right: 15px;"><a href="about.html" style="font-weight: normal; text-decoration: none; color: inherit;">About</a></span>
    <span><a href="contactus.html" style="font-weight: normal; text-decoration: none; color: inherit;">Contact Us</a></span>
</span>
        </div>
    </header>

    <div class="message">
        <c:choose>
            <c:when test="${status eq 'success'}">
                <p class="success">Admin Registered Successfully</p>
                <a href="${pageContext.request.contextPath}/adminLogin.html" class="retry-link">Admin</a>
            </c:when>
            <c:otherwise>
                <p class="failed">Admin Registration Unsuccessful</p>
              <c:if test="${not empty errorMessage}">
    <div style="color: crimson; font-weight: bold; padding: 12px; text-align: center; background-color: #ffe6e6; border: 1px solid red; width: 70%; margin: 0 auto; border-radius: 10px;">
        ${errorMessage}
    </div>
</c:if>              

                <a href="${pageContext.request.contextPath}/registeradmin.html" class="retry-link">Try again</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
