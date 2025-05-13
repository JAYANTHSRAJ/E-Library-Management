package in.ineuron.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcUtil {

    private static final Logger logger = Logger.getLogger(JdbcUtil.class.getName());

    private JdbcUtil() {
    }

    public static Connection getJdbcConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the MySQL driver is available
            String url = "jdbc:mysql://localhost:3306/elibrary"; // Database URL
            String username = "root"; // Your MySQL username
            String password = "ROOT"; // Your MySQL password
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "JDBC Driver not found", e);
            throw new SQLException("Failed to load JDBC driver.", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to establish a connection to the database", e);
            throw e;
        }
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing connection", e);
            }
        }
    }

    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing statement", e);
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing ResultSet", e);
            }
        }
    }

    public static void cleanUp(Connection con, Statement stmt, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(con);
    }
}
