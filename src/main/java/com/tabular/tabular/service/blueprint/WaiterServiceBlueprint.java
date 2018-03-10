package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Waiter;

import java.util.List;

public interface WaiterServiceBlueprint {
    Waiter queryWaiterById(long waiterId);
    List<Waiter> queryWaiterUnderOwnerByName(long ownerId, String name);
    List<Waiter> queryWaiterByOwner(long ownerId);
    boolean modifyWaiterName(long waiterId, String name);
    long createWaiter(long ownerId, String name);
}
