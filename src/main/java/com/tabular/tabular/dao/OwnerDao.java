package com.tabular.tabular.dao;

import com.tabular.tabular.entity.Owner;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OwnerDao {
    int insertOwner(@Param("ownerId") long ownerId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("restaurantId") long restaurantId);
    Owner queryOwnerById(long ownerId);
    List<Owner> queryOwnerByFirstName(String firstName);
    List<Owner> queryOwnerByLastName(String lastName);
    List<Owner> queryOwnerByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    List<Owner> queryOwnerByRestaurant(long restaurantId);
    List<Owner> queryAll();
    int modifyOwnerFirstName(@Param("ownerId") long ownerId, @Param("firstName") String firstName);
    int modifyOwnerLastName(@Param("ownerId") long ownerId, @Param("lastName") String lastName);
    int modifyOwnerFullName(@Param("ownerId") long ownerId, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
