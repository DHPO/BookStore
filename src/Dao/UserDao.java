package Dao;

import entities.UserEntity;

import java.util.List;

/**
 * Created by jimmy on 17-5-23.
 */
public interface UserDao{
    List<UserEntity> getUsers();
    int insertUser(UserEntity u);
    int updateUser(UserEntity u);
    int deleteUser(short id);
    UserEntity getUserByName(String name);
}

