package Servlet;

import Database.DatabaseAccess;
import org.json.JSONException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/update")
public class ServletUpdate extends HttpServlet {
    @Resource(name  = "jdbc/bookstore")
    private DataSource ds;

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JSONException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String table = request.getParameter("table");
        switch (table) {
            case "orders":
                int orderid = Integer.parseInt(request.getParameter("orderid"));
                int userid = Integer.parseInt(request.getParameter("userid"));
                out.println(DatabaseAccess.updateOrder(ds, orderid, userid));
                break;
            case "user": {
                int id = Integer.parseInt(request.getParameter("id"));
                String email = request.getParameter("email");
                String name = request.getParameter("name");
                int result = DatabaseAccess.updateUser(ds, id, name, email);
                out.println(result);
                System.out.println(result);
                break;
            }
            case "book": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                out.println(DatabaseAccess.updateBook(ds, id, name, price));
                break;
            }
            case "orderItem": {
                int id = Integer.parseInt(request.getParameter("orderid"));
                int bookid = Integer.parseInt(request.getParameter("bookid"));
                int amount = Integer.parseInt(request.getParameter("amount"));
                out.println(DatabaseAccess.updateOrderItem(ds, id, bookid, amount));
                break;
            }
            default:
                System.out.println("bad table");
                out.println(0);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
