package Dao.impl;

import Dao.BookDao;
import entities.BookEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by jimmy on 17-5-27.
 */
public class BookDaoImpl  extends HibernateDaoSupport implements BookDao{
    private BasicMovement basicMovement;

    public void setBasicMovement(BasicMovement b){basicMovement = b;}

    public List<BookEntity> getBooks(){
        return (List<BookEntity>) getHibernateTemplate().find("from BookEntity ");
    }

    public int deleteBook(short id){
        try{
            BookEntity oldBook = getHibernateTemplate().load(BookEntity.class, id);
            getHibernateTemplate().delete(oldBook);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertBook(BookEntity book){
        return basicMovement.insert(book);
    }

    public int updateBook(BookEntity book){
        return basicMovement.update(book);
    }
}