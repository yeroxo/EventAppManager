package com.example.productList.repos;

import com.example.productList.model.EventItem;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo  extends CrudRepository<EventItem,Long> {
}
