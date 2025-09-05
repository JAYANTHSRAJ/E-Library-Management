<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Request Status</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .failure {
            color: red;
            font-weight: bold;
        }
        .error {
            margin-top: 15px;
            font-size: 14px;
            color: #555;
        }
        a {
            margin-top: 20px;
            display: inline-block;
            padding: 10px 20px;
            background: #007bff;
            color: white;
            border-radius: 6px;
            text-decoration: none;
        }
        a:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>Student Request Status</h2>

    <%
        String status = (String) request.getAttribute("status");
        String errorMessage = (String) request.getAttribute("errorMessage");

        if (status != null && status.equalsIgnoreCase("success")) {
    %>
        <p class="success">✅ Request submitted successfully!</p>
    <%
        } else {
    %>
        <p class="failure">❌ Request submission failed.</p>
    <%
            if (errorMessage != null) {
    %>
        <p class="error">Error: <%= errorMessage %></p>
    <%
            }
        }
    %>

    <a href="student_registration.jsp">Go Back</a>
</div>
</body>
</html>
