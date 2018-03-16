package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long createCustomer(String name) {
        return customerService.createCustomer(name);
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Customer getCustomerById(@RequestParam("id") long customerId) {
        return customerService.queryCustomerById(customerId);
    }

    @RequestMapping(value = "/modify:name", method = RequestMethod.PATCH)
    public boolean modifyCustomerName(@RequestBody Map<String, String> payload) {
        return customerService.modifyCustomerName(Long.parseLong(payload.get("id")), payload.get("name"));
    }
}
