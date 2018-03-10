package com.tabular.tabular.service;

import com.tabular.tabular.dao.CustomerDao;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.exception.NoSuchCustomerException;
import com.tabular.tabular.service.blueprint.CustomerServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceBlueprint {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerDao customerDao;

    @Override
    public long createCustomer(String name) {
        if(Validator.validateName(name)) {
            String username = UUID.randomUUID().toString();
            long userId = userService.createUserAsCustomer(username, "password");
            customerDao.insertCustomer(userId, name);
            return userId;
        }else {
            return -1;
        }
    }

    @Override
    public boolean createCustomer(long customerId, String name) {
        if(Validator.validateUserId(customerId) && Validator.validateName(name) && queryCustomerById(customerId) == null) {
            customerDao.insertCustomer(customerId, name);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Customer queryCustomerById(long customerId) {
        if(Validator.validateUserId(customerId)) {
            return customerDao.queryCustomerById(customerId);
        }else {
            return null;
        }
    }

    @Override
    public List<Customer> queryCustomerByName(String name) {
        if(Validator.validateName(name)) {
            return customerDao.queryCustomerByName(name);
        } else {
            return null;
        }
    }

    @Override
    public boolean modifyCustomerName(long customerId, String name) {
        if(Validator.validateUserId(customerId) && Validator.validateName(name)) {
            int result = customerDao.modifyCustomerName(customerId, name);
            return result == 1;
        }else {
            return false;
        }
    }
}
