package com.example.productList.controller;

import com.example.productList.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {
    @Autowired
    ShopService shopService;

    @ResponseBody
    @RequestMapping(path = "/item/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("description") String text) {
        shopService.create(text);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(path = "/item/status/{id}", method = RequestMethod.PATCH)
    public boolean check(@PathVariable("id") Long id){
        return shopService.check(id);
    }

    @ResponseBody
    @RequestMapping(path = "/item/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        shopService.remove(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("items", shopService.listAllItems());
        return "main-list";
    }
}
