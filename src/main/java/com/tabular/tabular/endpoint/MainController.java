package com.tabular.tabular.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "*")
    @ResponseBody
    public HttpStatus allFallback() {
        return HttpStatus.NOT_FOUND; //404
    }
}
