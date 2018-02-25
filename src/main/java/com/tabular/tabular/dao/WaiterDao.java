package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Waiter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WaiterDao {
    int insertWaiter(@Param("waiterId") long waiterId, @Param("name") String name, @Param("ownerId") long ownerId);
    Waiter queryWaiterById(long waiterId);
    List<Waiter> queryWaiterByName(String name);
    List<Waiter> queryWaiterUnderOwnerByName(@Param("ownerId") long ownerId, @Param("name") String name);
    List<Waiter> queryWaiterByOwner(long ownerId);
    List<Waiter> queryAll();
    int modifyWaiterName(@Param("waiterId") long waiterId, @Param("name") String name);
}
