package SpringMVC;

import entities.BookEntity;
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

    @RequestMapping(value = "/getBook")
    public @ResponseBody
    List<BookEntity> getBooks(){
        return appService.getBooks();
    }

    @RequestMapping(value = "/updateBook")
    public @ResponseBody int updateBook(BookEntity book){
        return appService.updateBook(book);
    }

    @RequestMapping(value = "/insertBook")
    public @ResponseBody int insertBook(BookEntity book){
        return appService.insertBook(book);
    }

    @RequestMapping(value = "/deleteBook")
    public @ResponseBody int deleteBook(short id){
        return appService.deleteBook(id);
    }
}

