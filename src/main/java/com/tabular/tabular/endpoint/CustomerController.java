package com.tabular.tabular.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @RequestMapping("/signup")
    public void signUp() {

    }

    @RequestMapping("/login")
    public void login() {

    }
}
