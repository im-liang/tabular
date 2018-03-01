package com.tabular.tabular.dao;

import com.tabular.tabular.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int createUser(final User user);
    User queryUserById(long id);
    User queryUserByUsername(String username);
    List<User> queryUserByRole(int role);
    List<User> queryUserByStatus(int status);
    List<User> queryAll();
    int modifyUsername(@Param("userId") long userId, @Param("username") String username);
    int modifyUserPassword(@Param("userId") long userId, @Param("password") String password);
    int modifyUserRole(@Param("userId") long userId, @Param("role") int role);
    int modifyUserStatus(@Param("userId") long userId, @Param("status") int status);
    int deleteUserById(long userId);
    int deleteUserByName(String username);
}
