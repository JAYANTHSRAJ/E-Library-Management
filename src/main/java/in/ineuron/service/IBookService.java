package in.ineuron.service;
import java.util.List;

import in.ineuron.dto.BookDto;

public interface IBookService {
    public String addbook(BookDto book);
    public String deletebook(Integer bookId);
    public List<BookDto> searchBooksByAuthor(String bookAuthor);
    public List<BookDto> searchbookbyname(String bookName);
    public List<BookDto> searchbookbygenre(String bookGenre);
    public String updatebook(BookDto book);
}
