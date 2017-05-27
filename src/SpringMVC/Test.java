package SpringMVC;

import entities.BookEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class Test {
    private DaoTest daoTest;

    public void setDaoTest(DaoTest daoTest){this.daoTest = daoTest;}

    @RequestMapping(value = "/test")
    public @ResponseBody List<BookEntity> test(){
        System.out.println(daoTest);
        return daoTest.getAllBooks();
    }

    @Transactional
    @RequestMapping(value = "/testSave")
    public @ResponseBody short testSave(){
         return daoTest.saveBook();
    }
}

class DaoTest extends HibernateDaoSupport{
    @Transactional
    public short saveBook(){
        BookEntity book = new BookEntity();
        book.setName("test");
        return (short) getHibernateTemplate().save(book);
    }

    public List<BookEntity> getAllBooks(){
        List<BookEntity> list = (List<BookEntity>) getHibernateTemplate().find("from BookEntity ");
        return list;
    }
}


