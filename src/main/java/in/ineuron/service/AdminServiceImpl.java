package in.ineuron.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.ineuron.dto.AdminDto;
import in.ineuron.util.JdbcUtil;

public class AdminServiceImpl implements IAdminService {

    @Override
    public String registeradmin(AdminDto admin) {
        String status = "failed";

        String insertSQL = "INSERT INTO admindb (adminId, adminName, adminEmail, adminPassword) VALUES (?, ?, ?, ?)";

        try (
            Connection connection = JdbcUtil.getJdbcConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
        ) {
            pstmt.setInt(1, admin.getAdminId());
            pstmt.setString(2, admin.getAdminName());
            pstmt.setString(3, admin.getAdminEmail());
            pstmt.setString(4, admin.getAdminPassword());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
                status = "success";
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return status;
    }
}
