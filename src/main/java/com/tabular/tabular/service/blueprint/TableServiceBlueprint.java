package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Table;

import java.util.List;

public interface TableServiceBlueprint {
    long insertTable(Table table);
    Table queryTableById(long tableId);
    Table queryRestaurantTableByName(String name, long restaurantId);
    List<Table> queryRestaurantTableByLimit(long restaurantId, int numberLimit);
    List<Table> queryRestaurantTableByLessLimit(long restaurantId, int numberLimit);
    List<Table> queryRestaurantTableByMoreLimit(long restaurantId, int numberLimit);
    List<Table> queryRestaurantTableByLimitRange(long restaurantId, int lessThan, int moreThan);
    List<Table> queryTableByRestaurant(long restaurantId);
    List<Table> queryAll();
}
