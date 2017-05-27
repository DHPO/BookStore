package Dao;

import Database.MySession;
import entities.BookEntity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public ArrayList<BookEntity> getBooks(){
        Session session = MySession.getSession();
        List bookList;
        try{
            session.beginTransaction();
            bookList = session.createQuery("from BookEntity").list();
            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return (ArrayList<BookEntity>) bookList;
    }

    public int updateBook(BookEntity book){
        Session session = MySession.getSession();
        BookEntity oldBook;
        try{
            session.beginTransaction();
            oldBook = session.load(BookEntity.class, book.getId());
            if(oldBook != null){
                oldBook.setName(book.getName());
                oldBook.setPrice(book.getPrice());
                oldBook.setImg(book.getImg());
                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return oldBook == null?0:1;
    }

    public int insertBook(BookEntity book){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            result = 1;
        }catch (Exception e){
            result = 0;
        }finally{
            session.close();
        }
        return result;
    }

    public int deleteBook(short id){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            BookEntity book = session.load(BookEntity.class, id);
            session.delete(book);
            session.getTransaction().commit();
            result = 1;
        }catch(Exception e){
            result = 0;
        }finally {
            session.close();
        }
        return result;
    }
}
