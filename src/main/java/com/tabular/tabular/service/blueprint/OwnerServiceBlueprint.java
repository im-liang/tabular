package com.tabular.tabular.service.blueprint;

import com.tabular.tabular.entity.Owner;

import java.util.List;

public interface OwnerServiceBlueprint {
    Owner queryOwnerById(long ownerId);
    List<Owner> queryOwnerByRestaurant(long restaurantId);
    boolean modifyOwnerFullName(long ownerId, String firstName, String lastName);
    long createOwner(String firstName, String lastName, long restaurantId);
}
