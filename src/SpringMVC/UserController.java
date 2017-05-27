package SpringMVC;

import Dao.UserDao;
import entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by jimmy on 17-5-23.
 */
@Controller
public class UserController {
    private UserDao userDao = new UserDao();
    public void setUserDao(UserDao userDao){this.userDao = userDao;}

    @RequestMapping(value = "/getUsers")
    public @ResponseBody
    ArrayList<UserEntity> getBooks(){
        return userDao.getUsers();
    }

    @RequestMapping(value = "/updateUser")
    public @ResponseBody int updateBook(UserEntity user){
        return userDao.updateUser(user);
    }

    @RequestMapping(value = "/insertUser")
    public @ResponseBody int insertBook(UserEntity user){
        return userDao.insertUser(user);
    }

    @RequestMapping(value = "/deleteUser")
    public @ResponseBody int deleteBook(short id){
        return userDao.deleteUser(id);
    }
}
