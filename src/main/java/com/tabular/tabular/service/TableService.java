package com.tabular.tabular.service;

import com.tabular.tabular.dao.TableDao;
import com.tabular.tabular.entity.Table;
import com.tabular.tabular.service.blueprint.TableServiceBlueprint;
import com.tabular.tabular.service.utility.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TableService implements TableServiceBlueprint {
    @Autowired
    private TableDao tableDao;

    @Override
    public long insertTable(String name, int numberLimit, long restaurantId) {
        if(Validator.validateName(name) && Validator.validateUserId(restaurantId)) {
            Table table = new Table(name, numberLimit, restaurantId);
            return tableDao.insertTable(table);
        }else {
            return -1;
        }
    }
    @Override
    public Table queryTableById(long tableId) {
        if(Validator.validateUserId(tableId)) {
            return tableDao.queryTableById(tableId);
        } else {
            return null;
        }
    }
    @Override
    public Table queryRestaurantTableByName(String name, long restaurantId) {
        if(Validator.validateName(name) && Validator.validateUserId(restaurantId)) {
            return tableDao.queryRestaurantTableByName(name, restaurantId);
        }else {
            return null;
        }
    }
    @Override
    public List<Table> queryRestaurantTableByLimit(long restaurantId, int numberLimit) {
        if(Validator.validateUserId(restaurantId) && numberLimit > 0) {
            return tableDao.queryRestaurantTableByLimit(restaurantId, numberLimit);
        } else {
            return null;
        }
    }
    @Override
    public List<Table> queryRestaurantTableByLessLimit(long restaurantId, int numberLimit) {
        if(Validator.validateUserId(restaurantId) && numberLimit > 0) {
            return tableDao.queryRestaurantTableByLessLimit(restaurantId, numberLimit);
        } else {
            return null;
        }
    }
    @Override
    public List<Table> queryRestaurantTableByMoreLimit(long restaurantId, int numberLimit) {
        if(Validator.validateUserId(restaurantId) && numberLimit > 0) {
            return tableDao.queryRestaurantTableByMoreLimit(restaurantId, numberLimit);
        } else {
            return null;
        }
    }
    @Override
    public List<Table> queryRestaurantTableByLimitRange(long restaurantId, int lessThan, int moreThan) {
        if(Validator.validateUserId(restaurantId) &&  lessThan >=0 && moreThan >= lessThan) {
            return tableDao.queryRestaurantTableByLimitRange(restaurantId, lessThan, moreThan);
        } else {
            return null;
        }
    }
    @Override
    public List<Table> queryTableByRestaurant(long restaurantId) {
        if(Validator.validateUserId(restaurantId)) {
            return tableDao.queryTableByRestaurant(restaurantId);
        }else {
            return null;
        }
    }
    @Override
    public List<Table> queryAll() {
        return tableDao.queryAll();
    }
}
