package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.User;
import com.tabular.tabular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/create:owner", method = RequestMethod.POST)
    public Map<String, String> createUserAsOwner(@RequestBody Map<String, String> payload) {
        long id = userService.createUserAsOwner(payload.get("username"), payload.get("password"));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @RequestMapping(value = "/create:waiter", method = RequestMethod.POST)
    public Map<String, String> createUserAsWaiter(@RequestBody Map<String, String> payload) {
        long id = userService.createUserAsWaiter(payload.get("username"), payload.get("password"));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @RequestMapping(value = "/create:customer", method = RequestMethod.POST)
    public Map<String, String> createUserAsCustomer(@RequestBody Map<String, String> payload) {
        long id = userService.createUserAsCustomer(payload.get("username"), payload.get("password"));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public User findUserById(@RequestParam("id") long userId) {
        return userService.queryUserById(userId);
    }

    @RequestMapping(value = "/:username", method = RequestMethod.GET)
    public User findUserByUsername(@RequestParam("username") String username) {
        return userService.queryUserByUsername(username);
    }

    @RequestMapping(value = "/modify:username", method = RequestMethod.PATCH)
    public Map<String, String> modifyUsername(@RequestBody Map<String, String> payload) {
        boolean result = userService.changeUsername(Long.parseLong(payload.get("id")), payload.get("username"));

        HashMap<String, String> map = new HashMap<>();
        map.put("modified", String.valueOf(result));
        return map;
    }

    @RequestMapping(value = "/modify:password", method = RequestMethod.PATCH)
    public Map<String, String> modifyPassword(@RequestBody Map<String, String> payload) {
        boolean result = userService.changePassword(Long.parseLong(payload.get("id")), payload.get("password"));

        HashMap<String, String> map = new HashMap<>();
        map.put("modified", String.valueOf(result));
        return map;
    }
}
