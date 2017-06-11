package SpringMVC;

import entities.BookEntity;
import entities.BookSimple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jimmy on 17-5-22.
 */
@Controller
public class BookController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getBookSimple")
    public @ResponseBody
    List<BookSimple> getBooks(){
        return appService.getBooks();
    }

    @RequestMapping(value = "/updateBook")
    public @ResponseBody int updateBook(BookEntity book, HttpSession session){
        return appService.updateBook(book, session);
    }

    @RequestMapping(value = "/insertBook")
    public @ResponseBody int insertBook(BookEntity book, HttpSession session){
        return appService.insertBook(book, session);
    }

    @RequestMapping(value = "/deleteBook")
    public @ResponseBody int deleteBook(short id, HttpSession session){
        return appService.deleteBook(id, session);
    }

    @RequestMapping(value = "/search")
    public @ResponseBody List<BookSimple> searchBook(String name){
        return appService.findBooksByName(name);
    }

    @RequestMapping(value = "/getBook")
    public @ResponseBody List<BookEntity> getBooksDetail(){return appService.getDetailBooks();}

    @RequestMapping(value = "/getBookById")
    public @ResponseBody
    Map<String, Object> getBookById(short id, HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        map.put("book", appService.getBookById(id));
        map.put("num", appService.getBookNumInCart(id, session));
        return map;
    }

    @RequestMapping(value = "/updateBookNumInCart")
    public @ResponseBody int updateBookNumInCart(short id, Integer num, HttpSession session){
        return appService.updateBookNumInCart(id, num, session);
    }
}

