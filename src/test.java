import Database.MySession;
import entities.BookEntity;
import entities.UserEntity;
import org.hibernate.Session;
import org.json.JSONException;

import java.sql.SQLException;

public class test {
    public static void main(String argv[]) throws ClassNotFoundException, SQLException, JSONException {
        Session session = MySession.getSession();
        try{
            session.beginTransaction();
            Short bookid = 2;
            BookEntity book = session.load(BookEntity.class, bookid);
            Short userid = 3;
            UserEntity user = session.load(UserEntity.class, userid);
            System.out.println(book.getName() + user.getName());
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }
}
