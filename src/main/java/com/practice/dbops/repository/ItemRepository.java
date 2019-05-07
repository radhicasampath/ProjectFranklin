package com.practice.dbops.repository;

import com.practice.dbops.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findItemsByOrderId(Long orderId);

}
