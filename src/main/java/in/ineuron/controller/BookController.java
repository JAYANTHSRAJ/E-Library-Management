package in.ineuron.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.BookDto;
import in.ineuron.service.IBookService;
import in.ineuron.servicefactory.BookServiceFactory;

@WebServlet("/BookController/*")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IBookService bookservice = BookServiceFactory.getbookservice();

        if (request.getRequestURI().endsWith("searchbyauthor")) {
            String authorname = request.getParameter("book_author");
            List<BookDto> books = bookservice.searchBooksByAuthor(authorname);
            request.setAttribute("books", books);
            RequestDispatcher rd = request.getRequestDispatcher("../displaybookbyauthor.jsp");
            rd.forward(request, response);
        }

        if (request.getRequestURI().endsWith("searchbyname")) {
            String bookname = request.getParameter("book_name");
            List<BookDto> books = bookservice.searchbookbyname(bookname);
            request.setAttribute("books", books);
            RequestDispatcher rd = request.getRequestDispatcher("../displaybookbyauthor.jsp");
            rd.forward(request, response);
        }

        if (request.getRequestURI().endsWith("searchbygenre")) {
            String genrename = request.getParameter("book_genre");
            System.out.println("Genre received: " + genrename);
            List<BookDto> books = bookservice.searchbookbygenre(genrename);
            request.setAttribute("books", books);
            RequestDispatcher rd = request.getRequestDispatcher("../displaybookbyauthor.jsp");
            rd.forward(request, response);
            System.out.println("Genre received: " + genrename);

        }

        if (request.getRequestURI().endsWith("addbook")) {
            BookDto book = new BookDto();

            String idStr = request.getParameter("book_id");
            String quantityStr = request.getParameter("book_quantity");

            if (idStr == null || idStr.trim().isEmpty() || quantityStr == null || quantityStr.trim().isEmpty()) {
                request.setAttribute("status", "Book ID or Quantity is missing");
                request.getRequestDispatcher("../addbookstatus.jsp").forward(request, response);
                return;
            }

            try {
                book.setBookId(Integer.parseInt(idStr));
                book.setBookQuantity(Integer.parseInt(quantityStr));
            } catch (NumberFormatException e) {
                request.setAttribute("status", "Invalid numeric input for ID or Quantity");
                request.getRequestDispatcher("../addbookstatus.jsp").forward(request, response);
                return;
            }

            book.setBookName(request.getParameter("book_name"));
            book.setBookAuthor(request.getParameter("book_author"));
            book.setBookGenre(request.getParameter("book_genre"));

            String status = bookservice.addbook(book);
            request.setAttribute("status", status);
            request.getRequestDispatcher("../addbookstatus.jsp").forward(request, response);
        }

        if (request.getRequestURI().endsWith("updatebook")) {
            BookDto book = new BookDto();

            String idStr = request.getParameter("book_id");
            String quantityStr = request.getParameter("book_quantity");

            if (idStr == null || idStr.trim().isEmpty() || quantityStr == null || quantityStr.trim().isEmpty()) {
                request.setAttribute("status", "Book ID or Quantity is missing");
                request.getRequestDispatcher("../updatebookstatus.jsp").forward(request, response);
                return;
            }

            try {
                book.setBookId(Integer.parseInt(idStr));
                book.setBookQuantity(Integer.parseInt(quantityStr));
            } catch (NumberFormatException e) {
                request.setAttribute("status", "Invalid numeric input for ID or Quantity");
                request.getRequestDispatcher("../updatebookstatus.jsp").forward(request, response);
                return;
            }

            book.setBookName(request.getParameter("book_name"));
            book.setBookAuthor(request.getParameter("book_author"));
            book.setBookGenre(request.getParameter("book_genre"));

            String status = bookservice.updatebook(book);
            request.setAttribute("status", status);
            request.getRequestDispatcher("../updatebookstatus.jsp").forward(request, response);
        }

        if (request.getRequestURI().endsWith("deletebook")) {
            String idStr = request.getParameter("book_id");

            if (idStr == null || idStr.trim().isEmpty()) {
                request.setAttribute("status", "Book ID is missing");
                request.getRequestDispatcher("../deletebookstatus.jsp").forward(request, response);
                return;
            }

            int bid;
            try {
                bid = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                request.setAttribute("status", "Invalid Book ID format");
                request.getRequestDispatcher("../deletebookstatus.jsp").forward(request, response);
                return;
            }

            String status = bookservice.deletebook(bid);
            request.setAttribute("status", status);
            request.getRequestDispatcher("../deletebookstatus.jsp").forward(request, response);
        }
    }
}
