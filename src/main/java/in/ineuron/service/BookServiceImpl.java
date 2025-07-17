package in.ineuron.service;

import java.util.List;

import in.ineuron.dao.IBookDao;
import in.ineuron.daofactory.BookDaoFactory;
import in.ineuron.dto.BookDto;
import in.ineuron.servicefactory.*;

public class BookServiceImpl implements IBookService {
    
    private IBookDao bookdao;
    

    // Constructor - initializes once
    public BookServiceImpl() {
        this.bookdao = BookDaoFactory.getbookdao();
    }

    @Override
    public String addbook(BookDto book) {
       // bookdao = BookDaoFactory.getbookdao();
        return bookdao.addbook(book);
    }

    @Override
    public String deletebook(Integer bookId) {
       // bookdao = BookDaoFactory.getbookdao();
        return bookdao.deletebook(bookId);
    }

    @Override
    public List<BookDto> searchBooksByAuthor(String bookAuthor) {
       // bookdao = BookDaoFactory.getbookdao();
        return bookdao.searchbookbyauthor(bookAuthor);
    }

    @Override
    public List<BookDto> searchbookbyname(String bookName) {
       // bookdao = BookDaoFactory.getbookdao();
        return bookdao.searchbookbyname(bookName);
    }

    @Override
    public List<BookDto> searchbookbygenre(String bookGenre) {
       // bookdao = BookDaoFactory.getbookdao();
        return bookdao.searchbookbygenre(bookGenre);
    }

    
    @Override
    public String updatebook(BookDto book) {
      //  bookdao = BookDaoFactory.getbookdao();
        return bookdao.updatebook(book);
    }
}
