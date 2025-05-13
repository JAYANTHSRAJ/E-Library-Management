package in.ineuron.dto;

import java.io.Serializable;

public class BookDto implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	Integer bookId;
	String bookName;
	String bookAuthor;
	String bookGenre;
	Integer bookQuantity;
	
	@Override
	public String toString() {
		return "BookDto [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookGenre="
				+ bookGenre + ", bookQuantity=" + bookQuantity + "]";
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public Integer getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(Integer bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	
	
	
}