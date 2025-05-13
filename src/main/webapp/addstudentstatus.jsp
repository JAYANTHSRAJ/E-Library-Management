<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Details</title>
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
        .operations {
            position: absolute;
        }
        .operations {
            padding-left: 350px;
        }
        .operations select {
            height: 35px;
            width: 200px;
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
    </style>
</head>
<body background="<%= request.getContextPath() %>/StudentSearch.jpg" style="background-repeat: no-repeat; background-size: cover;">
    <header>
        <div class="logo"><img src="<%= request.getContextPath() %>/logo.png" height="100px" width="100px"></div>
        <div class="text"><h1> E-Library </h1> 
          <span>
    <span style="padding-right: 15px;"><a href="<%= request.getContextPath() %>adminoperations.html" style="font-weight: normal; text-decoration: none; color: inherit;">Home</a></span>
    <span style="padding-right: 15px;"><a href="#" style="font-weight: normal; text-decoration: none; color: inherit;">About</a></span>
    <span><a href="#" style="font-weight: normal; text-decoration: none; color: inherit;">Contact Us</a></span>
</span>
                
            
        </div>
        
    </header>
	<c:choose>
		<c:when test="${status eq 'Student added successfully.'}">
			<h1 style="color:green;text-align:center">Student Record Added Successfully</h1>
			<center> <a href="<%= request.getContextPath() %>/addstudent.html">Add another Student</a></center>
		</c:when>
		<c:otherwise>
			<h1 style="color:red;text-align:center">Student Record Addition Unsuccessful</h1>
			 <center> <a href="<%= request.getContextPath() %>/addstudent.html">Try again</a></center>
			
			<c:if test="${not empty status}">
			    <p style="color:red;text-align:center">Error: ${status}</p>
			</c:if>
		</c:otherwise>
	</c:choose>
</body>
</html>