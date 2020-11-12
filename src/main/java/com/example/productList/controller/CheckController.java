package com.example.productList.controller;

import com.example.productList.Service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckController {
    @Autowired
    CheckService checkServise;

    @ResponseBody
    @RequestMapping(path = "/item/{id}/status", method = RequestMethod.POST)
    public boolean check(@PathVariable("id") int id){
        boolean status = checkServise.check(id);
        return status;
    }
}
