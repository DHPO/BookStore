package SpringMVC;

import entities.BookEntity;
import entities.BookSimple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public @ResponseBody int updateBook(BookEntity book){
        return appService.updateBook(book);
    }

    @RequestMapping(value = "/insertBook")
    public @ResponseBody int insertBook(BookEntity book){
        System.out.println(book.getName());
        return appService.insertBook(book);
    }

    @RequestMapping(value = "/deleteBook")
    public @ResponseBody int deleteBook(short id){
        return appService.deleteBook(id);
    }

    @RequestMapping(value = "/search")
    public @ResponseBody List<BookSimple> searchBook(String name){
        return appService.findBooksByName(name);
    }

    @RequestMapping(value = "/getBook")
    public @ResponseBody List<BookEntity> getBooksDetail(){return appService.getDetailBooks();}

    @RequestMapping(value = "/getBookById")
    public @ResponseBody BookEntity getBookById(short id){
        return appService.getBookById(id);
    }
}

