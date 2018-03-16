package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Restaurant;
import com.tabular.tabular.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces={"application/json"}, consumes="application/json")
    public Map<String, String> createRestaurant(@RequestBody Restaurant restaurant) {
        long id = restaurantService.createRestaurant(restaurant);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Restaurant findRestaurantById(@RequestParam("id") long restaurantId) {
        return restaurantService.queryRestaurantById(restaurantId);
    }

    @RequestMapping(value = "/:name", method = RequestMethod.GET)
    public Restaurant findRestaurantByName(@RequestParam("name") String name) {
        return restaurantService.queryRestaurantByName(name);
    }

    @RequestMapping(value = "/:phone", method = RequestMethod.GET)
    public Restaurant findRestaurantByPhone(@RequestParam("phone") String phone) {
        return restaurantService.queryRestaurantByName(phone);
    }

    @RequestMapping(value = "/modify:name", method = RequestMethod.PATCH, produces={"application/json"}, consumes="application/json")
    public Map<String, String> modifyRestaurantName(@RequestBody Map<String, String> payload) {
        boolean result = restaurantService.modifyRestaurantName(Long.parseLong(payload.get("id")), payload.get("name"));

        HashMap<String, String> map = new HashMap<>();
        map.put("modified", String.valueOf(result));
        return map;
    }

    @RequestMapping(value = "/modify:phone", method = RequestMethod.PATCH, produces={"application/json"}, consumes="application/json")
    public Map<String, String> modifyRestaurantPhone(@RequestBody Map<String, String> payload) {
        boolean result = restaurantService.modifyRestaurantPhone(Long.parseLong(payload.get("id")), payload.get("phone"));

        HashMap<String, String> map = new HashMap<>();
        map.put("modified", String.valueOf(result));
        return map;
    }
}
