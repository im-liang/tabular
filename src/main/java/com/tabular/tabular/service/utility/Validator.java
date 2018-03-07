package com.tabular.tabular.service.utility;

public class Validator {
    public static boolean validateRestaurantId(long restaurantId) {
        if(restaurantId < 0) {
            return false;
        }
        return true;
    }

    public static boolean validateRestaurantName(String restaurantName) {
        if(restaurantName == null || restaurantName.equals("")) {
            return false;
        }
        return true;
    }
    
    public static boolean validatePhone(String phone) {
        if(phone == null || phone.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean validateUserId(long userId) {
        if(userId < 0) {
            return false;
        }
        return true;
    }

    public static boolean validateUsername(String username) {
        if(username == null || username.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    public static boolean validatePassword(String password) {
        if(password == null || password.equals("")) {
            return false;
        }else {
            return true;
        }
    }

    public static boolean validateName(String name) {
        if(name == null || name.equals("")) {
            return false;
        }else {
            return true;
        }
    }

}
