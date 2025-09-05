package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.IssueDto;
import in.ineuron.util.JdbcUtil;

import java.util.List;
import java.util.ArrayList;

public class IssueBookDaoImpl implements IIssueBookDao {

  
    @Override
    public String issuebook(IssueDto issue) {
        String issueDateColumnName = "issueDate";
        String dueDateColumnName   = "dueDate";

        try (Connection connection = JdbcUtil.getJdbcConnection();
             PreparedStatement pstmtCheckIssue = connection.prepareStatement(
                     "SELECT issueId FROM issuedb WHERE bookId=? AND studentId=? AND returnStatus IS NULL");
             PreparedStatement pstmtSelectQty  = connection.prepareStatement(
                     "SELECT bookQuantity FROM bookdb WHERE bookId=?");
             PreparedStatement pstmtInsertIssue = connection.prepareStatement(
                     "INSERT INTO issuedb (bookId, `" + issueDateColumnName + "`, `" +
                             dueDateColumnName + "`, studentId) VALUES (?,?,?,?)");
             PreparedStatement pstmtUpdateQty  = connection.prepareStatement(
                     "UPDATE bookdb SET bookQuantity=? WHERE bookId=?")) {

            // 1) Same student already has this book?
            pstmtCheckIssue.setInt(1, issue.getBookId());
            pstmtCheckIssue.setInt(2, issue.getStudentId());
            try (ResultSet rs = pstmtCheckIssue.executeQuery()) {
                if (rs.next()) return "failed5";   // already issued
            }

            // 2) Is the book in stock?
            pstmtSelectQty.setInt(1, issue.getBookId());
            try (ResultSet rsQty = pstmtSelectQty.executeQuery()) {
                if (!rsQty.next())          return "failed4";        // no book row
                int qty = rsQty.getInt(1);
                if (qty == 0)               return "failed1";        // out of stock

                // 3) Insert issue record
                pstmtInsertIssue.setInt(1, issue.getBookId());
                pstmtInsertIssue.setString(2, issue.getBookIssueDate());
                pstmtInsertIssue.setString(3, issue.getBookIssueDueDate());
                pstmtInsertIssue.setInt(4, issue.getStudentId());
                if (pstmtInsertIssue.executeUpdate() != 1) return "failed3";

                // 4) Decrease quantity
                pstmtUpdateQty.setInt(1, qty - 1);
                pstmtUpdateQty.setInt(2, issue.getBookId());
                if (pstmtUpdateQty.executeUpdate() != 1) return "failed2";

                return "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed";                // generic DB error
        }
    }


    @Override
    public IssueDto returnbook(Integer bookId, Integer studentId) {
        String issueDateCol = "issueDate", dueDateCol = "dueDate";
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement markReturned = conn.prepareStatement(
                 "UPDATE issuedb SET returnStatus='Returned', returnDate=CURRENT_DATE() " +
                 "WHERE bookId=? AND studentId=? AND returnStatus IS NULL");
             PreparedStatement qtySelect = conn.prepareStatement(
                 "SELECT bookQuantity FROM bookdb WHERE bookId=?");
             PreparedStatement qtyUpdate = conn.prepareStatement(
                 "UPDATE bookdb SET bookQuantity = ? WHERE bookId=?");
             PreparedStatement fetch = conn.prepareStatement(
                 "SELECT bookId, `" + issueDateCol + "`, `" + dueDateCol + "`, studentId " +
                 "FROM issuedb WHERE bookId=? AND studentId=? AND returnStatus='Returned' " +
                 "ORDER BY issueId DESC LIMIT 1")) {

            // 1) Mark returned
            markReturned.setInt(1, bookId);
            markReturned.setInt(2, studentId);
            int updated = markReturned.executeUpdate();
            if (updated == 0) return null;  // no row to return

            // 2) Increase stock
            qtySelect.setInt(1, bookId);
            try (ResultSet rs = qtySelect.executeQuery()) {
                if (rs.next()) {
                    int q = rs.getInt(1);
                    qtyUpdate.setInt(1, q + 1);
                    qtyUpdate.setInt(2, bookId);
                    qtyUpdate.executeUpdate();
                }
            }

            // 3) Fetch the returned record
            fetch.setInt(1, bookId);
            fetch.setInt(2, studentId);
            try (ResultSet rs2 = fetch.executeQuery()) {
                if (rs2.next()) {
                    IssueDto dto = new IssueDto();
                    dto.setBookId(rs2.getInt("bookId"));
                    dto.setBookIssueDate(rs2.getString(issueDateCol));
                    dto.setBookIssueDueDate(rs2.getString(dueDateCol));
                    dto.setStudentId(rs2.getInt("studentId"));
                    dto.setReturnDate(java.time.LocalDate.now().toString());
                    return dto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  @Override
    public String checkDue(Integer studentId) {
        String dueDateColumnName = "dueDate";

        try (Connection connection = JdbcUtil.getJdbcConnection();
             PreparedStatement pstmt = connection.prepareStatement(
               "SELECT `" + dueDateColumnName + "` " +
               "FROM issuedb WHERE studentId=? AND returnStatus IS NULL")) {

            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public List<IssueDto> fetchIssuedBooks(Integer studentId) {
        String sql = 
          "SELECT i.bookId, b.bookName, i.issueDate, i.dueDate "
        + "  FROM issuedb i "
        + "  JOIN bookdb b ON i.bookId=b.bookId "
        + " WHERE i.studentId=? AND i.returnStatus IS NULL";

        List<IssueDto> list = new ArrayList<>();
        try (Connection conn = JdbcUtil.getJdbcConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    IssueDto dto = new IssueDto();
                    dto.setBookId(rs.getInt("bookId"));
                    dto.setBookName(rs.getString("bookName"));
                    dto.setBookIssueDate(rs.getString("issueDate"));
                    dto.setBookIssueDueDate(rs.getString("dueDate"));
                    dto.setStudentId(studentId);
                    list.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
