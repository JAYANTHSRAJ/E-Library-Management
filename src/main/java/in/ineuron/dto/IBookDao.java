package in.ineuron.dto;
import java.util.List;

public interface IBookDao {
    public String addbook(BookDto book);
    public String deletebook(Integer bookId);
    public List<BookDto> searchbookbyauthor(String bookAuthor);
    public List<BookDto> searchbookbyname(String bookName);
    public List<BookDto> searchbookbygenre(String bookGenre);
    public String updatebook(BookDto book);    
}
