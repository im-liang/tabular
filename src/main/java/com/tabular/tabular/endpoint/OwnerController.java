package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Owner;
import com.tabular.tabular.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long createOwner(String firstName, String lastName, long restaurantId) {
        return ownerService.createOwner(firstName, lastName, restaurantId);
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Owner findOwnerById(@RequestParam("id") long ownerId) {
        return ownerService.queryOwnerById(ownerId);
    }

    @RequestMapping(value = "/list:restaurantId", method = RequestMethod.GET)
    public List<Owner> findOwnerByRestaurant(@RequestParam("restaurantId") long restaurantId) {
        return ownerService.queryOwnerByRestaurant(restaurantId);
    }

    @RequestMapping(value = "/modify:fullName", method = RequestMethod.PATCH)
    public Map<String, String> modifyOwnerFullName(@RequestBody Map<String, String> payload) {
        boolean result = ownerService.modifyOwnerFullName(Long.parseLong(payload.get("id")), payload.get("firstName"), payload.get("lastName"));

        HashMap<String, String> map = new HashMap<>();
        map.put("modified", String.valueOf(result));
        return map;
    }
}
