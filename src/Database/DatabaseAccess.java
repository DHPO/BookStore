package Database;

import org.json.JSONException;
import javax.sql.DataSource;
import java.sql.*;

public class DatabaseAccess {
    public static String getUser(DataSource ds) throws SQLException, JSONException{
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from user");
        return ResultSetToJSON.convert(statement.executeQuery());
    }

    public static String getOrder(DataSource ds) throws SQLException, JSONException{
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from orders");
        return ResultSetToJSON.convert(statement.executeQuery());
    }

    public static String getOrderItem(DataSource ds) throws SQLException, JSONException{
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from orderItem");
        return ResultSetToJSON.convert(statement.executeQuery());
    }

    public static String getBook(DataSource ds) throws SQLException, JSONException{
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("select * from book");
        return ResultSetToJSON.convert(statement.executeQuery());
    }

    public static int updateUser(DataSource ds, int id, String name, String email) throws SQLException, JSONException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("update user set name = ?, email = ? where id = ?");
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setInt(3, id);
        return statement.executeUpdate();
    }

    public static int updateBook(DataSource ds, int id, String name, double price) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("update book set name = ?, price = ? where id = ?");
        statement.setString(1, name);
        statement.setDouble(2, price);
        statement.setInt(3, id);
        return statement.executeUpdate();
    }

    public static int updateOrder(DataSource ds, int orderid, int userid) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("update orders set userid = ? where orderid = ?");
        statement.setInt(1, userid);
        statement.setInt(2, orderid);
        return statement.executeUpdate();
    }

    public static int updateOrderItem(DataSource ds, int orderid, int bookid, int amount) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("update orderItem set amount = ? where orderid = ? and bookid = ?");
        statement.setInt(1, amount);
        statement.setInt(2, orderid);
        statement.setInt(3, bookid);
        return statement.executeUpdate();
    }

    public static int delete(DataSource ds, String table, int id, int id2) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement;
        switch(table){
            case "user":
                statement = conn.prepareStatement("delete from user where id = ?");
                statement.setInt(1, id);
                break;
            case "book":
                statement = conn.prepareStatement("delete from book where id = ?");
                statement.setInt(1, id);
                break;
            case "orders":
                statement = conn.prepareStatement("delete from orders where orderid = ?");
                statement.setInt(1, id);
                break;
            case "orderItem":
                statement = conn.prepareStatement("delete from orderItem where orderid = ? and bookid = ?");
                statement.setInt(1, id);
                statement.setInt(2,id2);
                break;
            default:
                statement = conn.prepareStatement("delete from user where false");
        }
        return statement.executeUpdate();
    }

    public static int insertBook(DataSource ds, String name, double price) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into book (name, price) values (?, ?)");
        statement.setString(1, name);
        statement.setDouble(2, price);
        return statement.executeUpdate();
    }

    public static int insertUser(DataSource ds, String name, String email) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into user (name, email) values(?, ?)");
        statement.setString(1, name);
        statement.setString(2, email);
        return statement.executeUpdate();
    }

    public static int insertOrder(DataSource ds, int userid) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into orders (userid) values (?)");
        statement.setInt(1, userid);
        return statement.executeUpdate();
    }

    public static int insertOrderItem(DataSource ds, int orderid, int bookid, int amount) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("insert into orderItem (orderid, bookid, amount) values(?, ?, ?)");
        statement.setInt(1, orderid);
        statement.setInt(2, bookid);
        statement.setInt(3, amount);
        return statement.executeUpdate();
    }
}
