package com.tabular.tabular.service;

import com.tabular.tabular.dao.CustomerDao;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.exception.NoSuchCustomerException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    UserService userService;
    @Autowired
    CustomerDao customerDao;

    public long createCustomer(String name) {
        String username = UUID.randomUUID().toString();
        long userId = userService.createUserAsCustomer(username, "password");
        customerDao.insertCustomer(userId, name);
        return userId;
    }

    public long createCustomer(long customerId, String name) {
        customerDao.insertCustomer(customerId, name);
        return customerId;
    }

    public Customer queryCustomerById(long customerId) {
        return customerDao.queryCustomerById(customerId);
    }

    public List<Customer> queryCustomerByName(String name) {
        return customerDao.queryCustomerByName(name);
    }

    public boolean modifyCustomerName(long customerId, String name) {
        int result = customerDao.modifyCustomerName(customerId, name);
        if(result == 1) {
            return true;
        }else if(result <= 0) {
            throw new NoSuchCustomerException("no customer was created using this id");
        }else {
            throw new TooManyResultsException("Too many customer linked to 1 id");
        }
    }
}
