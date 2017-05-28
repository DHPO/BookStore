package SpringMVC;

import entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
@Controller
public class UserController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getUsers")
    public @ResponseBody
    List<UserEntity> getBooks(){
        return appService.getUsers();
    }

    @RequestMapping(value = "/updateUser")
    public @ResponseBody int updateBook(UserEntity user){
        return appService.updateUser(user);
    }

    @RequestMapping(value = "/insertUser")
    public @ResponseBody int insertBook(UserEntity user){
        return appService.insertUser(user);
    }

    @RequestMapping(value = "/deleteUser")
    public @ResponseBody int deleteBook(short id){
        return appService.deleteUser(id);
    }
}
