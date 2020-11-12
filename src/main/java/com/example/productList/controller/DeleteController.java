package com.example.productList.controller;

import com.example.productList.Service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeleteController {
    @Autowired
    ListService listService;

    @ResponseBody
    @RequestMapping(path = "/item/{id}/delete", method = RequestMethod.POST)
    public void delete(@PathVariable("id") int id){
        listService.remove(id);
    }
}
