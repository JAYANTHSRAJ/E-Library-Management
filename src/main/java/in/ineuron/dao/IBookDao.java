package in.ineuron.dao;

import in.ineuron.dto.BookDto;

public interface IBookDao {
	public String addbook(BookDto book);
	public String deletebook(Integer bookId);
	public BookDto searchbookbyauthor(String bookAuthor);
	public BookDto searchbookbyname(String bookName);
	public BookDto searchbookbygenre(String bookGenre);
	public String updatebook(BookDto book);	
}