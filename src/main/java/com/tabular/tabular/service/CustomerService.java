package com.tabular.tabular.service;

import com.tabular.tabular.dao.CustomerDao;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.exception.customer.NoSuchCustomerException;
import com.tabular.tabular.exception.user.NoSuchUserException;
import com.tabular.tabular.service.blueprint.CustomerServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceBlueprint {

    private final CustomerDao customerDao;
    private final UserService userService;

    @Autowired
    public CustomerService(UserService userService, CustomerDao customerDao) {
        this.userService = userService;
        this.customerDao = customerDao;
    }

    @Override
    public long createCustomer(String name) {
        String username = UUID.randomUUID().toString();
        long userId = userService.createUserAsCustomer(username, "password");
        customerDao.insertCustomer(userId, name);
        return userId;
    }

    @Override
    public long createCustomer(long customerId, String name) {
        if(!userService.isUserExist(customerId)) {
            throw new NoSuchUserException("user with " + customerId + "does not exist");
        }
        customerDao.insertCustomer(customerId, name);
        return customerId;
    }

    @Override
    public Customer queryCustomerById(long customerId) {
        Customer customer = customerDao.queryCustomerById(customerId);
        if(customer == null) {
            throw new NoSuchCustomerException("customer with " + customerId + "does not exist");
        }
        return customer;
    }

    @Override
    public List<Customer> queryCustomerByName(String name) {
        return customerDao.queryCustomerByName(name);
    }

    @Override
    public boolean modifyCustomerName(long customerId, String name) {
        if(!isCustomerExist(customerId)) {
            throw new NoSuchCustomerException("customer with " + customerId + "does not exist");
        }
        int result = customerDao.modifyCustomerName(customerId, name);
        return result == 1;
    }

    private boolean isCustomerExist(long customerId) {
        return customerDao.queryCustomerById(customerId) != null;
    }
}
