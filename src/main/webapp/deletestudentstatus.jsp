<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <br/><br/> <br/><br/>
    
	<c:choose>
		<c:when test="${status eq 'success'}">
			<h1 style="color:green;text-align:center">Student Record Deleted Successfully</h1>
		</c:when>
		<c:otherwise>
			<h1 style="color:red;text-align:center">Student Record Deletion UnSuccessful</h1>
			<c:if test="${not empty errorMessage}">
				<p style="color:red;text-align:center">${errorMessage}</p>
			</c:if>
		</c:otherwise>
	</c:choose>
	<center><a href="student_operations.html">Back to Operations</a></center>
</body>
</html>