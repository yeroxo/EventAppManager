package com.example.productList.repos;

import com.example.productList.model.ShopItem;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo  extends CrudRepository<ShopItem,Long> {
}
