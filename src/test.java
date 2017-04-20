import Database.DatabaseAccess;
import org.json.JSONException;
import java.sql.*;

public class test {
    public static void main(String argv[]) throws ClassNotFoundException, SQLException, JSONException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bookstore";
        String user = "root";
        String pwd = "20030344";
        Connection con = DriverManager.getConnection(url, user, pwd);

        PreparedStatement statement = con.prepareStatement("delete from book where id = ?");
        statement.setInt(1,7);
        int result = statement.executeUpdate();

        System.out.println(result);
    }
}
