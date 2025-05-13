package in.ineuron.service;


import in.ineuron.dao.IBookDao;
import in.ineuron.daofactory.BookDaoFactory;
import in.ineuron.dto.BookDto;

public class BookServiceImpl implements IBookService
{
	private IBookDao bookdao;
	@Override
	public String addbook(BookDto book) {
		
		bookdao = BookDaoFactory.getbookdao();
		return bookdao.addbook(book);
	}

	@Override
	public String deletebook(Integer bookId) {
		
		bookdao = BookDaoFactory.getbookdao();
		return bookdao.deletebook(bookId);
	}

	@Override
	public BookDto searchbookbyauthor(String bookAuthor) {
		
		bookdao = BookDaoFactory.getbookdao();
		return bookdao.searchbookbyauthor(bookAuthor);
	}

	@Override
	public BookDto searchbookbyname(String bookName) {
		
		return bookdao.searchbookbyname(bookName);
	}

	@Override
	public BookDto searchbookbygenre(String bookGenre) {

		bookdao = BookDaoFactory.getbookdao();
		return bookdao.searchbookbygenre(bookGenre);
	}

	@Override
	public String updatebook(BookDto book) {
	
		bookdao = BookDaoFactory.getbookdao();
		return bookdao.updatebook(book);
	}
	
}