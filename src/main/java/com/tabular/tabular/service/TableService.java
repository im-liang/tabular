package com.tabular.tabular.service;

import com.tabular.tabular.dao.TableDao;
import com.tabular.tabular.entity.Table;
import com.tabular.tabular.exception.restaurant.NoSuchRestaurantException;
import com.tabular.tabular.exception.table.NoSuchTableException;
import com.tabular.tabular.service.blueprint.TableServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements TableServiceBlueprint {

    private final TableDao tableDao;
    private final RestaurantService restaurantService;

    @Autowired
    public TableService(RestaurantService restaurantService, TableDao tableDao) {
        this.restaurantService = restaurantService;
        this.tableDao = tableDao;
    }

    @Override
    public long insertTable(Table table) {
        if(!restaurantService.isRestaurantExist(table.getRestaurantId())) {
            throw new NoSuchRestaurantException("restaurant with " + table.getRestaurantId() + " does not exist");
        }
        return tableDao.insertTable(table);
    }
    @Override
    public Table queryTableById(long tableId) {
        Table table = tableDao.queryTableById(tableId);
        if(table == null) {
            throw new NoSuchTableException("table with id: " + tableId + " does not exist");
        }
        return table;
    }
    @Override
    public Table queryRestaurantTableByName(String name, long restaurantId) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryRestaurantTableByName(name, restaurantId);
    }
    @Override
    public List<Table> queryRestaurantTableByLimit(long restaurantId, int numberLimit) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryRestaurantTableByLimit(restaurantId, numberLimit);
    }
    @Override
    public List<Table> queryRestaurantTableByLessLimit(long restaurantId, int numberLimit) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryRestaurantTableByLessLimit(restaurantId, numberLimit);
    }
    @Override
    public List<Table> queryRestaurantTableByMoreLimit(long restaurantId, int numberLimit) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryRestaurantTableByMoreLimit(restaurantId, numberLimit);
    }
    @Override
    public List<Table> queryRestaurantTableByLimitRange(long restaurantId, int lessThan, int moreThan) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryRestaurantTableByLimitRange(restaurantId, lessThan, moreThan);
    }
    @Override
    public List<Table> queryTableByRestaurant(long restaurantId) {
        if(!restaurantService.isRestaurantExist(restaurantId)) {
            throw new NoSuchRestaurantException("restaurant with " + restaurantId + " does not exist");
        }
        return tableDao.queryTableByRestaurant(restaurantId);
    }
    @Override
    public List<Table> queryAll() {
        return tableDao.queryAll();
    }

    public boolean isTableExist(long tableId) {
        return tableDao.queryTableById(tableId) != null;
    }
}
