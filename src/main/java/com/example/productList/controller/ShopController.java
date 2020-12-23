package com.example.productList.controller;

import com.example.productList.Service.EventService;
import com.example.productList.model.MyUser;
import com.example.productList.repos.MyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class ShopController {

    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    EventService eventService;

    @Autowired
    MyUserRepository users;

    @ResponseBody
    @RequestMapping(path = "/item/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("description") String name,
                            @ModelAttribute("startDate") String start,
                            @ModelAttribute("endDate") String end) throws ParseException {
        logger.info("/item/add Name:" + name + " startDate:" + start + " endDate:" + end);
        eventService.create(name, start, end);
        return new ModelAndView("redirect:/");
    }

    @ResponseBody
    @RequestMapping(path = "/item/status/{id}", method = RequestMethod.PATCH)
    public boolean check(@PathVariable("id") Long id) {
        logger.info("CHECK EVENT:" + id);
        return eventService.check(id);
    }

    @PostMapping(path = "/item/update/{id}")
    public String changeName(@PathVariable("id") Long id,@ModelAttribute("name") String name,
                              @ModelAttribute("dateStart") String dateStart,@ModelAttribute("dateEnd") String dateEnd) throws ParseException {
        logger.info("PATH EVENT:" + name + dateStart + dateEnd );
        eventService.update(id,name,dateStart,dateEnd);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(path = "/item/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        logger.info("DELETE EVENT:" + id);
        eventService.remove(id);
    }

    @GetMapping(path = "/")
    public String list(Model model) {
        model.addAttribute("items", eventService.listAllItems());
        logger.info("GET EVENT LIST");
        return "main-list";
    }


    @GetMapping("/registration")
    public String registration() {
        logger.info("REGISTRATION GET");
        return "registration";
    }

    @ResponseBody
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("login") String login, @ModelAttribute("password") String password) {
        logger.info("REGISTRATION POST");
        MyUser registered = users.findByLogin(login);
        if (registered != null) {
            logger.error("User already has");
            return "registration";
        }
        if (password == null || password.isEmpty()) {
            logger.error("Null or Empy passwor");
            return "registration";
        }
        MyUser user = new MyUser(login, password);
        users.save(user);
        logger.info("REGISTRATION COMPLETE");
        return "redirect:/";
    }
}
