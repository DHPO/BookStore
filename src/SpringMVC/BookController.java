package SpringMVC;

import Dao.BookDao;
import entities.BookEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by jimmy on 17-5-22.
 */
@Controller
public class BookController {
    private BookDao bookDao = new Dao.BookDao();

    public void setBookDao(BookDao bookDao){this.bookDao = bookDao;}

    @RequestMapping(value = "/getBook")
    public @ResponseBody ArrayList<BookEntity> getBooks(){
        return bookDao.getBooks();
    }

    @RequestMapping(value = "/updateBook")
    public @ResponseBody int updateBook(BookEntity book){
        return bookDao.updateBook(book);
    }

    @RequestMapping(value = "/insertBook")
    public @ResponseBody int insertBook(BookEntity book){
        return bookDao.insertBook(book);
    }

    @RequestMapping(value = "/deleteBook")
    public @ResponseBody int deleteBook(short id){
        return bookDao.deleteBook(id);
    }
}

