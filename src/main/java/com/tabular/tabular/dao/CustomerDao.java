package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerDao {
    /**
     * create a customer entry with its id matched to user id
     *
     * @param customerId
     * @param name
     * @return affected row
     */
    int insertCustomer(@Param("customerId") long customerId, @Param("name") String name);

    /**
     * find a customer with its id matched to the customerId
     *
     * @param customerId
     * @return customer with matching id
     */
    Customer queryCustomerById(long customerId);

    /**
     * find a list of customers with its name matched to the name
     *
     * @param name
     * @return list of customers with matching name
     */
    List<Customer> queryCustomerByName(String name);

    /**
     * find all customers in the database
     *
     * @return list of customers
     */
    List<Customer> queryAll();

    /**
     * modify the name of a customer entry
     *
     * @param customerId
     * @param name
     * @return affected row
     */
    int modifyCustomerName(@Param("customerId") long customerId, @Param("name") String name);
}
