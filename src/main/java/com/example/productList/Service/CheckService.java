package com.example.productList.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService {
    @Autowired
    ListService listService;

    public boolean check(int itemId){
        boolean status = listService.listAllItems().get(itemId).getStatus();
        status = !status;
        listService.listAllItems().get(itemId).setStatus(status);
        return status;
    }
}
