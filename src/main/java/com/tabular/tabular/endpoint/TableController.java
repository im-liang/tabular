package com.tabular.tabular.endpoint;

import com.tabular.tabular.entity.Table;
import com.tabular.tabular.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map<String, String> createTable(@RequestBody Table table) {
        long id = tableService.insertTable(table);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public Table findTableById(@RequestParam("id") long tableId) {
        return tableService.queryTableById(tableId);
    }

    @RequestMapping(value = "/:name", method = RequestMethod.GET)
    public Table findRestaurantTableByName(@RequestParam("name") String name, @RequestParam("restaurantId") long restaurantId) {
        return tableService.queryRestaurantTableByName(name, restaurantId);
    }

    @RequestMapping(value = "/:restaurant", method = RequestMethod.GET)
    public List<Table> findTableByRestaurant(@RequestParam("restaurantId") long restaurantId) {
        return tableService.queryTableByRestaurant(restaurantId);
    }
}
