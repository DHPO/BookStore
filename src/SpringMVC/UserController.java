package SpringMVC;

import entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
@Controller
public class UserController {
    private AppService.AppService appService;

    public void setAppService(AppService.AppService appService){this.appService = appService;}

    @RequestMapping(value = "/getUser")
    public @ResponseBody
    List<UserEntity> getUsers(){
        return appService.getUsers();
    }

    @RequestMapping(value = "/updateUser")
    public @ResponseBody int updateUser(UserEntity user){
        return appService.updateUser(user);
    }

    @RequestMapping(value = "/insertUser")
    public @ResponseBody int insertUser(UserEntity user){
        return appService.insertUser(user);
    }

    @RequestMapping(value = "/deleteUser")
    public @ResponseBody int deleteUser(short id){
        return appService.deleteUser(id);
    }

    @RequestMapping(value = "/login")
    public @ResponseBody int login(String username, String password, HttpSession session){
        UserEntity user = appService.login(username, password);
        if(user == null)
            return 0;
        session.setAttribute("user", user);
        return user.getRole();
    }

    public boolean loginCheck(HttpSession session){
        return (session.getAttribute("user") != null);
    }

    @RequestMapping(value = "/logout")
    public @ResponseBody int logout(HttpSession session){
        if(loginCheck(session)){
            session.setAttribute("user", null);
        }
        return 0;
    }

    @RequestMapping(value = "/register")
    public @ResponseBody int register(UserEntity user, HttpSession session){
        user.setRole(1);
        if(appService.insertUser(user) == 1){
            session.setAttribute("user", user);
            return user.getRole();
        }
        return 0;
    }
}
