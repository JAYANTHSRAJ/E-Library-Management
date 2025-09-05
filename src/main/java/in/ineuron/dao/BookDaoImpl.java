package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ineuron.dto.BookDto;
import in.ineuron.dto.IBookDao;
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
	public List<BookDto> searchbookbyauthor(String bookAuthor) {
	    List<BookDto> bookList = new ArrayList<>();
	    try {
	        connection = JdbcUtil.getJdbcConnection();
	        String sql = "SELECT * FROM bookdb WHERE bookAuthor=?";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, bookAuthor);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            BookDto book = new BookDto();
	            book.setBookId(rs.getInt("bookId"));
	            book.setBookName(rs.getString("bookName"));
	            book.setBookAuthor(rs.getString("bookAuthor"));
	            book.setBookGenre(rs.getString("bookGenre"));
	            book.setBookQuantity(rs.getInt("bookQuantity"));
	            bookList.add(book);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.closeConnection(connection);
	        JdbcUtil.closeResultSet(rs);
	        JdbcUtil.closeStatement(pstmt);
	    }
	    return bookList;
	}

	
		@Override
		public List<BookDto> searchbookbyname(String bookName) {
		    List<BookDto> bookList = new ArrayList<>();
		    try {
		        connection = JdbcUtil.getJdbcConnection();
		        String sql = "SELECT * FROM bookdb WHERE bookName=?";
		        pstmt = connection.prepareStatement(sql);
		        pstmt.setString(1, bookName);
		        rs = pstmt.executeQuery();
		        while (rs.next()) {
		            BookDto book = new BookDto();
		            book.setBookId(rs.getInt("bookId"));
		            book.setBookName(rs.getString("bookName"));
		            book.setBookAuthor(rs.getString("bookAuthor"));
		            book.setBookGenre(rs.getString("bookGenre"));
		            book.setBookQuantity(rs.getInt("bookQuantity"));
		            bookList.add(book);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        JdbcUtil.closeConnection(connection);
		        JdbcUtil.closeResultSet(rs);
		        JdbcUtil.closeStatement(pstmt);
		    }
		    return bookList;
		}

	
		
			@Override
			public List<BookDto> searchbookbygenre(String bookGenre) {
			    List<BookDto> bookList = new ArrayList<>();
			    try {
			        connection = JdbcUtil.getJdbcConnection();
			        String sql = "SELECT * FROM bookdb WHERE bookGenre=?";
			        pstmt = connection.prepareStatement(sql);
			        pstmt.setString(1, bookGenre);
			        rs = pstmt.executeQuery();
			        while (rs.next()) {
			            BookDto book = new BookDto();
			            book.setBookId(rs.getInt("bookId"));
			            book.setBookName(rs.getString("bookName"));
			            book.setBookAuthor(rs.getString("bookAuthor"));
			            book.setBookGenre(rs.getString("bookGenre"));
			            book.setBookQuantity(rs.getInt("bookQuantity"));
			            bookList.add(book);
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        JdbcUtil.closeConnection(connection);
			        JdbcUtil.closeResultSet(rs);
			        JdbcUtil.closeStatement(pstmt);
			    }
			    return bookList;
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