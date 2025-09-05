package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.ineuron.dto.AdminDto;
import in.ineuron.util.JdbcUtil;

public class AdminDaoImpl implements IAdminDao
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	@Override
	public String registeradmin(AdminDto admin) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlInsertQuery = "insert into admindb (adminId,adminName,adminEmail,adminPassword) values (?,?,?,?)";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlInsertQuery);
				pstmt.setInt(1, admin.getAdminId());
				pstmt.setString(2, admin.getAdminName());
				pstmt.setString(3, admin.getAdminEmail());
				pstmt.setString(4, admin.getAdminPassword());
				if(pstmt != null) {
					int rowsAffected = pstmt.executeUpdate();
					if(rowsAffected == 1) {
						return "success";
					}
				}

			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "failed";
	}
	
}
