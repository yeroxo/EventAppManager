package com.example.productList.controller;

import com.example.productList.Service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddController {
    @Autowired
    ListService listService;

    @RequestMapping(path = "/item/add", method = RequestMethod.POST)
    public String check(@ModelAttribute("description") String text){
        listService.create(text);
        return "redirect:/";
    }
}
