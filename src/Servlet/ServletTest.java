package Servlet;

import org.json.JSONException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/foo")
public class ServletTest extends HttpServlet {
    private SpringMVC.xkz xkz;
    private String msg;

    public void setXkz(SpringMVC.xkz xkz){this.xkz = xkz;}
    public void setMsg(String msg){this.msg = msg;}

    private void process(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, SQLException, JSONException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(msg);
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
