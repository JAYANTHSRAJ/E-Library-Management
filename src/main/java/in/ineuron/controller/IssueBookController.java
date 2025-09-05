package in.ineuron.controller;
<<<<<<< HEAD

import java.util.List;
=======
import java.util.List;

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.IssueDto;
import in.ineuron.service.IIssueBookService;
import in.ineuron.servicefactory.IssueServiceFactory;

@WebServlet("/IssueBookController/*")
public class IssueBookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        IIssueBookService bookService = IssueServiceFactory.getissuebookservice();
        IssueDto issueDto = null;
        RequestDispatcher rd = null;

        if (request.getRequestURI().endsWith("issuebook")) {
            issueDto = new IssueDto();
            try {
                Integer bid = Integer.parseInt(request.getParameter("book_id"));
                String bIssueDate = request.getParameter("issue_date");
                String bDueDate = request.getParameter("return_date");
                Integer sid = Integer.parseInt(request.getParameter("student_id"));

                issueDto.setBookId(bid);
                issueDto.setBookIssueDate(bIssueDate);
                issueDto.setBookIssueDueDate(bDueDate);
                issueDto.setStudentId(sid);

                String status = bookService.issuebook(issueDto);

                switch (status) {
                    case "success":
                        request.setAttribute("status", "success");
                        break;
                    case "failed1":
<<<<<<< HEAD
                        request.setAttribute("status", "failed1");
                        break;
                    case "failed2":
                        request.setAttribute("status", "failed2");
                        break;
                    case "failed3":
                        request.setAttribute("status", "failed3");
                        break;
                    case "failed4":
                        request.setAttribute("status", "failed4");
                        break;
                    case "failed5":
                        request.setAttribute("status", "failed5");
                        break;
                    default:
                        request.setAttribute("status", "failed");
=======
                        request.setAttribute("status", "failed1"); // Book out of stock
                        break;
                    case "failed2":
                        request.setAttribute("status", "failed2"); // Failed to update book quantity
                        break;
                    case "failed3":
                        request.setAttribute("status", "failed3"); // Failed to insert issue record
                        break;
                    case "failed4":
                        request.setAttribute("status", "failed4"); // Book not found
                        break;
                    case "failed5":
                        request.setAttribute("status", "failed5"); // Student has already issued this book
                        break;
                    default:
                        request.setAttribute("status", "failed"); // Generic failure
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
                        request.setAttribute("errorMessage", status);
                        break;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("status", "failed");
                request.setAttribute("errorMessage", "Invalid Book ID or Student ID format.");
            }
            rd = request.getRequestDispatcher("../issuebookstatus.jsp");
            rd.forward(request, response);

        } else if (request.getRequestURI().endsWith("returnbook")) {
            try {
                Integer bid = Integer.parseInt(request.getParameter("book_id"));
<<<<<<< HEAD
                Integer sidFromRequest = Integer.parseInt(request.getParameter("student_id"));

                // ✅ Get studentId from session
                Integer sidFromSession = (Integer) request.getSession().getAttribute("studentId");

                if (sidFromSession == null) {
                    request.setAttribute("error", "Session expired. Please login again.");
                    rd = request.getRequestDispatcher("../studentlogin.jsp");
                    rd.forward(request, response);
                    return;
                }

                if (!sidFromSession.equals(sidFromRequest)) {
                    request.setAttribute("error", "Unauthorized action. You can only return your own issued books.");
                    rd = request.getRequestDispatcher("../returnbookstatus.jsp");
                    rd.forward(request, response);
                    return;
                }

                issueDto = bookService.returnbook(bid, sidFromRequest);
                request.setAttribute("issuedto", issueDto);
                rd = request.getRequestDispatcher("../returnbookstatus.jsp");
                rd.forward(request, response);

=======
                Integer sid = Integer.parseInt(request.getParameter("student_id")); 

                issueDto = bookService.returnbook(bid, sid);

                request.setAttribute("issuedto", issueDto);
                rd = request.getRequestDispatcher("../returnbookstatus.jsp");
                rd.forward(request, response);
>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Book ID or Student ID format for return.");
                rd = request.getRequestDispatcher("../returnbookstatus.jsp");
                rd.forward(request, response);
            }
<<<<<<< HEAD
        }

        else if (request.getRequestURI().endsWith("checkdue")) {
            Integer sid = Integer.parseInt(request.getParameter("student_Id"));
            List<IssueDto> issuedList = bookService.fetchIssuedBooks(sid);
            request.setAttribute("issuedList", issuedList);
            rd = request.getRequestDispatcher("../checkduestatus.jsp");
            rd.forward(request, response);
        }
=======

        } 
     
        else if (request.getRequestURI().endsWith("checkdue")) {
           
            Integer sid = Integer.parseInt(request.getParameter("student_Id"));
         
            List<IssueDto> issuedList = bookService.fetchIssuedBooks(sid);
           
            request.setAttribute("issuedList", issuedList);
           
            rd = request.getRequestDispatcher("../checkduestatus.jsp");
            rd.forward(request, response);
        }

>>>>>>> 07de3710b1c3653f00f6f54cc55c7df5fae080e8
    }
}
