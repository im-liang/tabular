package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Customer;

import java.util.List;

public interface CustomerServiceBlueprint {
    long createCustomer(String name);
    long createCustomer(long customerId, String name);
    Customer queryCustomerById(long customerId);
    List<Customer> queryCustomerByName(String name);
    boolean modifyCustomerName(long customerId, String name);
}
