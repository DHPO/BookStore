package Servlet;

import Database.DatabaseAccess;
import org.json.JSONException;
import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/getOrder")
public class ServletGetOrder extends HttpServlet {
    @Resource(name = "jdbc/bookstore")
    private DataSource ds;

    private void process(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, SQLException, JSONException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(DatabaseAccess.getOrder(ds));
        out.close();
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
    }
}
