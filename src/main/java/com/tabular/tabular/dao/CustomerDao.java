package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerDao {
    int insertCustomer(@Param("customerId") long customerId, @Param("name") String name);
    Customer queryCustomerById(long id);
    List<Customer> queryCustomerByName(String name);
    List<Customer> queryAll();
    int modifyCustomerName(@Param("customerId") long customerId, @Param("name") String name);
}
