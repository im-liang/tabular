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
    private UserDao userDao;

    public User queryUserById(long userId) {
        checkId(userId);
        return userDao.queryUserById(userId);
    }

    public User queryUserByUsername(String username) {
        checkUsername(username);
        return userDao.queryUserByUsername(username);
    }

    public boolean changeUsername(long userId, String username){
        checkId(userId);
        checkUsername(username);
        if(queryUserByUsername(username) != null) {
            return false;
        }
        int affectedRow = userDao.modifyUsername(userId, username);
        return affectedRow == 1;
    }

    public boolean changePassword(long userId, String password){
        checkId(userId);
        checkPassword(password);
        int affectedRow = userDao.modifyUserPassword(userId, password);
        return affectedRow == 1;
    }

    public boolean deleteUserById(long userId){
        checkId(userId);
        int affectedRow = userDao.deleteUserById(userId);
        return affectedRow == 1;
    }

    public boolean deleteUserByName(String username){
        checkUsername(username);
        int affectedRow = userDao.deleteUserByName(username);
        return affectedRow == 1;
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
    public boolean activateUser(long userId){
        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.ACTIVE.getStatus());
        return affectedRow == 1;
    }

    /**
     * create a relation entry between appointment and user
     *
     * @param userId
     * @return affected row
     */
    public boolean deActivateUser(long userId){
        int affectedRow = userDao.modifyUserStatus(userId, UserStatusEnum.INACTIVE.getStatus());
        return affectedRow == 1;
    }

    private void checkId(long userId) {
        if(userId < 0) {
            throw new IllegalArgumentException("invalid user id");
        }
    }

    private void checkUsername(String username) {
        if(username == null || username.equals("")) {
            throw new IllegalArgumentException("invalid username");
        }
    }

    private void checkPassword(String password) {
        if(password == null || password.equals("")) {
            throw new IllegalArgumentException("invalid password");
        }
    }
}
