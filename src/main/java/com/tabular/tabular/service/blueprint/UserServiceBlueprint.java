package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.User;

public interface UserServiceBlueprint {
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

    /**
     * find user using the user id
     *
     * @param userId
     * @return user
     */
    User queryUserById(long userId);

    /**
     * find user using the username
     *
     * @param username
     * @return user
     */
    User queryUserByUsername(String username);

    /**
     * modify username of the user
     *
     * @param userId
     * @param username
     * @return boolean indicate if the modification is successful
     */
    boolean changeUsername(long userId, String username);

    /**
     * modify password of the user
     *
     * @param userId
     * @param password
     * @return boolean indicate if the modification is successful
     */
    boolean changePassword(long userId, String password);

    /**
     * delete the user using userId
     *
     * @param userId
     * @return boolean indicate if the deletion is successful
     */
    boolean deleteUserById(long userId);

    /**
     * delete the user using username
     *
     * @param username
     * @return boolean indicate if the deletion is successful
     */
    boolean deleteUserByName(String username);
}
