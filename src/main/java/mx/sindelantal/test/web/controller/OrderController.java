package mx.sindelantal.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("webOrders")
@RequestMapping("/order")
public class OrderController {
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        return "order/create";
    }
}
