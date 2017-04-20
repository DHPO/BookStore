package Servlet;

import Database.DatabaseAccess;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/insert")
public class ServletInsert extends HttpServlet {
    @Resource(name = "jdbc/bookstore")
    private DataSource ds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String table = request.getParameter("table");
        try {
            switch (table) {
                case "book":
                    String name = request.getParameter("name");
                    double price = Double.parseDouble(request.getParameter("price"));
                    out.println(DatabaseAccess.insertBook(ds, name, price));
                    break;
                case "user":
                    String username = request.getParameter("name");
                    String email = request.getParameter("email");
                    out.println(DatabaseAccess.insertUser(ds, username, email));
                    break;
                case "orders":
                    int userid = Integer.parseInt(request.getParameter("userid"));
                    out.println(DatabaseAccess.insertOrder(ds, userid));
                    break;
                case "orderItem":
                    int orderid = Integer.parseInt(request.getParameter("orderid"));
                    int bookid = Integer.parseInt(request.getParameter("bookid"));
                    int amount = Integer.parseInt(request.getParameter("amount"));
                    out.println(DatabaseAccess.insertOrderItem(ds, orderid, bookid, amount));
                    break;
                default:
                    out.println(0);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
