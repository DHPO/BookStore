package Dao;

import entities.UserEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
/*public class UserDao {
    public ArrayList<UserEntity> getUsers(){
        Session session = MySession.getSession();
        List userList;
        try{
            session.beginTransaction();
            userList = session.createQuery("from UserEntity").list();
            session.getTransaction().commit();

        }finally {
            session.close();
        }
        return (ArrayList<UserEntity>) userList;
    }

    public int updateUser(UserEntity user){
        Session session = MySession.getSession();
        UserEntity oldUser;
        try{
            session.beginTransaction();
            oldUser = session.load(UserEntity.class, user.getId());
            if(oldUser != null){
                oldUser.setName(user.getName());
                oldUser.setEmail(user.getEmail());
                session.getTransaction().commit();
            }
        }finally {
            session.close();
        }
        return oldUser == null?0:1;
    }

    public int insertUser(UserEntity user){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = 1;
        }catch (Exception e){
            result = 0;
        }finally{
            session.close();
        }
        return result;
    }

    public int deleteUser(short id){
        Session session = MySession.getSession();
        int result;
        try{
            session.beginTransaction();
            UserEntity user = session.load(UserEntity.class, id);
            session.delete(user);
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
*/

public interface UserDao{
    List<UserEntity> getUsers();
    int insertUser(UserEntity u);
    int updateUser(UserEntity u);
    int deleteUser(short id);
}

