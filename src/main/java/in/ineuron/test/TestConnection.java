package in.ineuron.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.ineuron.util.JdbcUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet("/testConnection")
public class TestConnection extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("=== TestConnection Servlet Started ===");
        System.out.println("Request received from: " + request.getRemoteAddr());
        
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            System.out.println("Attempting to get database connection...");
            Connection connection = JdbcUtil.getJdbcConnection();
            System.out.println("Database connection obtained successfully");
            
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Database metadata retrieved successfully");
            
            // Prepare response
            StringBuilder result = new StringBuilder();
            result.append("<!DOCTYPE html>");
            result.append("<html>");
            result.append("<head>");
            result.append("<title>Database Connection Test</title>");
            result.append("<style>");
            result.append("body { font-family: Arial, sans-serif; margin: 20px; }");
            result.append("h2 { color: #2c3e50; }");
            result.append(".success { color: #27ae60; }");
            result.append(".info { background-color: #f8f9fa; padding: 15px; border-radius: 5px; }");
            result.append("</style>");
            result.append("</head>");
            result.append("<body>");
            result.append("<h2>Database Connection Test</h2>");
            result.append("<p class='success'>Connection successful!</p>");
            result.append("<div class='info'>");
            result.append("<p><strong>Database Product:</strong> " + metaData.getDatabaseProductName() + "</p>");
            result.append("<p><strong>Database Version:</strong> " + metaData.getDatabaseProductVersion() + "</p>");
            result.append("<p><strong>Driver Name:</strong> " + metaData.getDriverName() + "</p>");
            result.append("<p><strong>Driver Version:</strong> " + metaData.getDriverVersion() + "</p>");
            result.append("</div>");
            result.append("</body>");
            result.append("</html>");
            
            System.out.println("Sending response...");
            out.println(result.toString());
            System.out.println("Response sent successfully");
            
            JdbcUtil.cleanUp(connection, null, null);  // Now works because cleanUp is defined
            System.out.println("Connection closed");
            
        } catch (Exception e) {
            System.err.println("=== Error in TestConnection servlet ===");
            System.err.println("Error message: " + e.getMessage());
            e.printStackTrace();
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            System.err.println("Stack trace: " + stackTrace);
            
            StringBuilder error = new StringBuilder();
            error.append("<!DOCTYPE html>");
            error.append("<html>");
            error.append("<head>");
            error.append("<title>Database Connection Error</title>");
            error.append("<style>");
            error.append("body { font-family: Arial, sans-serif; margin: 20px; }");
            error.append("h2 { color: #c0392b; }");
            error.append(".error { color: #c0392b; }");
            error.append(".stack-trace { background-color: #f8f9fa; padding: 15px; border-radius: 5px; white-space: pre-wrap; }");
            error.append("</style>");
            error.append("</head>");
            error.append("<body>");
            error.append("<h2>Database Connection Error</h2>");
            error.append("<p class='error'>Error: " + e.getMessage() + "</p>");
            error.append("<h3>Stack Trace:</h3>");
            error.append("<div class='stack-trace'>");
            error.append(stackTrace);
            error.append("</div>");
            error.append("</body>");
            error.append("</html>");
            
            out.println(error.toString());
        } finally {
            System.out.println("=== TestConnection Servlet Completed ===");
        }
    }
}
