package in.ineuron.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.AdminDto;
import in.ineuron.service.IAdminService;
import in.ineuron.servicefactory.AdminServiceFactory;
import in.ineuron.util.JdbcUtil;

@WebServlet("/AdminController/*")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IAdminService adminService = AdminServiceFactory.getadminservice();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.endsWith("AdminLogin")) {
            handleLogin(request, response);
        } else if (uri.endsWith("registeradmin")) {
            handleRegistration(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String adminId = request.getParameter("admin_Id");
    	String adminEmail = request.getParameter("admin_email");
    	String adminPassword = request.getParameter("admin_password");

        System.out.println(">>> Input Data >>>");
        System.out.println("Admin ID: " + adminId);
        System.out.println("Admin Email: " + adminEmail);
        System.out.println("Admin Password: " + adminPassword);

        if (adminId == null || adminEmail == null || adminPassword == null ||
        		adminId.trim().isEmpty() || adminEmail.trim().isEmpty() || adminPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All login fields are required.");
            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
            return;
        }

        try (Connection connection = JdbcUtil.getJdbcConnection()) {
            if (connection == null) {
                System.out.println("Database connection is null!");
                request.setAttribute("errorMessage", "Database connection error.");
                request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                return;
            }

            String sqlQuery = "SELECT adminEmail, adminPassword FROM admindb WHERE adminId = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {
                pstmt.setInt(1, Integer.parseInt(adminId.trim()));
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedEmail = rs.getString("adminEmail");
                        String storedPassword = rs.getString("adminPassword");

                        System.out.println(">>> DB Data >>>");
                        System.out.println("Stored Email: " + storedEmail);
                        System.out.println("Stored Password: " + storedPassword);

                       
                        adminEmail = adminEmail.trim();
                        adminPassword = adminPassword.trim();

                        if (adminEmail.equalsIgnoreCase(storedEmail.trim()) &&
                            adminPassword.equals(storedPassword.trim())) {
                         
                            System.out.println("Login successful!");
                            response.sendRedirect("../adminoperations.html");
                        } else {
                            System.out.println("Invalid credentials.");
                            request.setAttribute("errorMessage", "Invalid email or password.");
                            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("Admin ID not found.");
                        request.setAttribute("errorMessage", "Admin ID not found.");
                        request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Internal server error during login.");
            request.getRequestDispatcher("../adminVerificationFail.jsp").forward(request, response);
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AdminDto admin = new AdminDto();
           // admin.setAdminId(Integer.parseInt(request.getParameter("admin_id")));
            String adminIdStr = request.getParameter("admin_id");

            if (adminIdStr != null && !adminIdStr.trim().isEmpty()) {
                admin.setAdminId(Integer.parseInt(adminIdStr));
            } else {
                // Option 1: Throw error
               // throw new IllegalArgumentException("Admin ID is required and must be a number.");

                // Option 2 (alternative): Show custom error message to user
                 request.setAttribute("errorMessage", "Admin ID cannot be empty");
                 request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                 return;
            }

            admin.setAdminName(request.getParameter("admin_name"));
            admin.setAdminEmail(request.getParameter("admin_email"));
            admin.setAdminPassword(request.getParameter("admin_password"));

            String status = adminService.registeradmin(admin);

            request.setAttribute("status", "success".equals(status) ? "success" : "failed");
            request.getRequestDispatcher("../registeradminstatus.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "failed");
            request.getRequestDispatcher("../registeradminstatus.jsp").forward(request, response);
        }
    }
}
