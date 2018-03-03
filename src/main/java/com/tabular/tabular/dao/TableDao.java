package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableDao {
    int insertTable(final Table table);
    Table queryTableById(long tableId);
    Table queryRestaurantTableByName(@Param("name") String name, @Param("restaurantId") long restaurantId);
    List<Table> queryRestaurantTableByLimit(@Param("restaurantId") long restaurantId, @Param("numberLimit") int numberLimit);
    List<Table> queryRestaurantTableByLessLimit(@Param("restaurantId") long restaurantId, @Param("numberLimit") int numberLimit);
    List<Table> queryRestaurantTableByMoreLimit(@Param("restaurantId") long restaurantId, @Param("numberLimit") int numberLimit);
    List<Table> queryRestaurantTableByLimitRange(@Param("restaurantId") long restaurantId, @Param("lessThan") int lessThan, @Param("moreThan") int moreThan);
    List<Table> queryTableByRestaurant(long restaurantId);
    List<Table> queryAll();
}
