package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.User;

public interface UserServiceBlueprint {
    User queryUserById(long userId);
    User queryUserByUsername(String username);
    boolean changeUsername(long userId, String username);
    boolean changePassword(long userId, String password);
    boolean deleteUserById(long userId);
    boolean deleteUserByName(String username);

    /**
     * create a user which role is customer
     *
     * @param username
     * @param password
     * @return user id
     */
    long createUserAsCustomer(String username, String password);

    /**
     * create a user which role is waiter
     *
     * @param username
     * @param password
     * @return user id
     */
    long createUserAsWaiter(String username, String password);

    /**
     * create a user which role is owner
     *
     * @param username
     * @param password
     * @return user id
     */
    long createUserAsOwner(String username, String password);

    /**
     * create a relation entry between appointment and user
     *
     * @param userId
     * @return boolean
     */
    boolean activateUser(long userId);

    /**
     * create a relation entry between appointment and user
     *
     * @param userId
     * @return affected row
     */
    boolean deactivateUser(long userId);
}
