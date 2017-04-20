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

@WebServlet("/delete")
public class ServletDelete extends HttpServlet {
    @Resource(name = "jdbc/bookstore")
    private DataSource ds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String table = request.getParameter("table");
        PrintWriter out = response.getWriter();
        int id ;
        int id2 = -1;
        if(table.equals("orders") || table.equals("orderItem")){
            id = Integer.parseInt(request.getParameter("orderid"));
        }else{
            id = Integer.parseInt(request.getParameter("id"));
        }
        if(table.equals("orderItem")){
            id2 = Integer.parseInt(request.getParameter("bookid"));
        }
        try {
            System.out.println(id);
            System.out.println(id2);
            System.out.println(table);
            int result = DatabaseAccess.delete(ds, table, id, id2);
            out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
