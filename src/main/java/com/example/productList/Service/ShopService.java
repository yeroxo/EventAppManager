package com.example.productList.Service;

import com.example.productList.model.ShopItem;
import com.example.productList.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ItemRepo itemRepo;

    public Iterable<ShopItem> listAllItems() {
        return itemRepo.findAll();
    }

    public void create(String text) {
        ShopItem item = new ShopItem(null,text);
        itemRepo.save(item);
    }

    public void remove(Long id) {
        itemRepo.deleteById(id);
    }

    public ShopItem getItem(Long id) {
        return itemRepo.findById(id).get();
    }

    public boolean check(Long itemId) {
        ShopItem item = itemRepo.findById(itemId).get();
        if (item.getStatus())
            item.setStatus(false);
        else
            item.setStatus(true);
        itemRepo.save(item);
        return item.getStatus();
    }
}
