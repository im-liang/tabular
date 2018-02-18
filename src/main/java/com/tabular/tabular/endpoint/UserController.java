package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Customer;
import com.tabular.tabular.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/signup")
    public void signUp() {

    }

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
    private String findCustomer(@PathVariable("bookId") Long bookId, Model model) {
        if (bookId == null) {
            return "redirect:/book/list";
        }
        Customer customer = customerService.getById(bookId);
        if (customer == null) {
            return "forward:/book/list";
        }
        model.addAttribute("book", customer);
        return "detail";
    }
}
