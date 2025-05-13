package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.BookDto;
import in.ineuron.util.JdbcUtil;

public class BookDaoImpl implements IBookDao {
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    BookDto book;

  
    @Override
    public String addbook(BookDto book) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = JdbcUtil.getJdbcConnection();
            String sqlInsertQuery = "insert into bookdb (bookId, bookName, bookAuthor, bookGenre, bookQuantity) values (?, ?, ?, ?, ?)";
            if (connection != null) {
                pstmt = connection.prepareStatement(sqlInsertQuery);
                pstmt.setInt(1, book.getBookId());
                pstmt.setString(2, book.getBookName());
                pstmt.setString(3, book.getBookAuthor());
                pstmt.setString(4, book.getBookGenre());
                pstmt.setInt(5, book.getBookQuantity());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 1) {
                    return "success";
                }
            }
        } catch (SQLException e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {
                return "exists";
            }
            e.printStackTrace();
            return "failed";
        } finally {
            JdbcUtil.closeConnection(connection);
            JdbcUtil.closeStatement(pstmt);
        }
        return "failed";
    }

	@Override
	public String deletebook(Integer bookId) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlDeleteQuery = "delete from bookdb where bookId=?";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlDeleteQuery);
				pstmt.setInt(1, bookId);
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

	@Override
	public BookDto searchbookbyauthor(String bookAuthor) {
	
		try {
			
			connection = JdbcUtil.getJdbcConnection();
			String sqlSearchQuery = "select bookId,bookName,bookAuthor,bookGenre from bookdb where bookAuthor=?";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlSearchQuery);
				pstmt.setString(1, bookAuthor);
				if(pstmt != null) {
					rs = pstmt.executeQuery();
					if(rs != null) {
						if(rs.next()) {
							book = new BookDto();
							book.setBookId(rs.getInt(1));
							book.setBookName(rs.getString(2));
							book.setBookAuthor(rs.getString(3));
							book.setBookGenre(rs.getString(4));
							return book;
						}
					}
					else {
						return null;
					}
					
				}
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookDto searchbookbyname(String bookName) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlSearchQuery = "select bookId,bookName,bookAuthor,bookGenre from bookdb where bookName=?";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlSearchQuery);
				pstmt.setString(1, bookName);
				if(pstmt != null) {
					rs = pstmt.executeQuery();
					if(rs != null) {
						if(rs.next()) {
							book = new BookDto();
							book.setBookId(rs.getInt(1));
							book.setBookName(rs.getString(2));
							book.setBookAuthor(rs.getString(3));
							book.setBookGenre(rs.getString(4));
							return book;
						}
					}else {
						return null;
					}
				}
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookDto searchbookbygenre(String bookGenre) {
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlSearchQuery = "select bookId,bookName,bookAuthor,bookGenre from bookdb where bookGenre=?";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlSearchQuery);
				pstmt.setString(1, bookGenre);
				if(pstmt != null) {
					rs = pstmt.executeQuery();
					if(rs != null) {
						if(rs.next()) {
							book = new BookDto();
							book.setBookId(rs.getInt(1));
							book.setBookName(rs.getString(2));
							book.setBookAuthor(rs.getString(3));
							book.setBookGenre(rs.getString(4));
							return book;
						}
					}else {
						return null;
					}
					
				}
				
				
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updatebook(BookDto book){
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			String sqlUpdateQuery = "update bookdb set bookName=?,bookAuthor=?,bookGenre=?,bookQuantity=? where bookId=?";
			if(connection != null) {
				pstmt = connection.prepareStatement(sqlUpdateQuery);
				pstmt.setString(1, book.getBookName());
				pstmt.setString(2, book.getBookAuthor());
				pstmt.setString(3, book.getBookGenre());
				pstmt.setInt(4, book.getBookQuantity());
				pstmt.setInt(5, book.getBookId());
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