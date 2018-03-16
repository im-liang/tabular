package com.tabular.tabular.endpoint;

import com.tabular.tabular.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiter")
public class WaiterController {
    private final WaiterService waiterService;

    @Autowired
    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long createWaiter(long ownerId, String name) {
        return waiterService.createWaiter(ownerId, name);
    }
}
