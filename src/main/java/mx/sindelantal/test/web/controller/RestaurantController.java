package mx.sindelantal.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("webRestaurants")
@RequestMapping("/restaurant")
public class RestaurantController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "restaurant/list";
    }
    
    @RequestMapping(value = "/listByRating", method = RequestMethod.GET)
    public String listByRating(Model model) {
        return "restaurant/listByRating";
    }
    
    @RequestMapping(value = "/listByRC", method = RequestMethod.GET)
    public String listByRC(Model model) {
        return "restaurant/listByRC";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "restaurant/create";
    }

}