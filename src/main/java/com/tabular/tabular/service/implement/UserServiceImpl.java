package com.tabular.tabular.service.implement;

import com.tabular.tabular.dao.CustomerDao;
import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer getById(long customerId) {
        return customerDao.queryCustomerById(customerId);
    }
}
