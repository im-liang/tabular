package com.tabular.tabular.service;

import com.tabular.tabular.dao.UserDao;
import com.tabular.tabular.entity.User;
import com.tabular.tabular.enums.UserRoleEnum;
import com.tabular.tabular.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User queryUserById(long userId) {
        return userDao.queryUserById(userId);
    }

    public User queryUserByUsername(String username) {
        return userDao.queryUserByUsername(username);
    }

    int changeUsername(long userId, String username){
        return userDao.modifyUsername(userId, username);
    }

    int changePassword(long userId, String password){
        return userDao.modifyUserPassword(userId, password);
    }

    int deleteUserById(long userId){
        return userDao.deleteUserById(userId);
    }

    int deleteUserByName(String username){
        return userDao.deleteUserByName(username);
    }


    /**
     * create a user which role is customer
     *
     * @param username
     * @param password
     * @return user id
     */
    public long createUserAsCustomer(String username, String password) {
        User user = new User(username, password, UserRoleEnum.CUSTOMER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    /**
     * create a user which role is waiter
     *
     * @param username
     * @param password
     * @return user id
     */
    public long createUserAsWaiter(String username, String password) {
        User user = new User(username, password, UserRoleEnum.WAITER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    /**
     * create a user which role is owner
     *
     * @param username
     * @param password
     * @return user id
     */
    public long createUserAsOwner(String username, String password) {
        User user = new User(username, password, UserRoleEnum.OWNER.getRole(), UserStatusEnum.ACTIVE.getStatus());
        userDao.createUser(user);
        return user.getUserId();
    }

    /**
     * create a relation entry between appointment and user
     *
     * @param userId
     * @return boolean
     */
    boolean activateUser(long userId){
        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.ACTIVE.getStatus());
        if(affectedRow <= 0) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * create a relation entry between appointment and user
     *
     * @param userId
     * @return affected row
     */
    boolean deActivateUser(long userId){
        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.INACTIVE.getStatus());
        if(affectedRow <= 0) {
            return false;
        }else {
            return true;
        }
    }
}
