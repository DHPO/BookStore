package Dao.impl;

import Dao.UserDao;
import entities.UserEntity;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by jimmy on 17-5-28.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
    private BasicMovement basicMovement;

    public void setBasicMovement(BasicMovement b){basicMovement = b;}

    public List<UserEntity> getUsers(){
        return (List<UserEntity>) getHibernateTemplate().find("from UserEntity ");
    }

    public int deleteUser(short id){
        try{
            UserEntity oldUser = getHibernateTemplate().load(UserEntity.class, id);
            getHibernateTemplate().delete(oldUser);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int insertUser(UserEntity user){
        return basicMovement.insert(user);
    }

    public int updateUser(UserEntity user){
        return basicMovement.update(user);
    }
}
