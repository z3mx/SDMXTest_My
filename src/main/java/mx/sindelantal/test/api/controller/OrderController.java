package mx.sindelantal.test.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.sindelantal.test.annotation.RestApiController;
import mx.sindelantal.test.model.Order;
import mx.sindelantal.test.service.OrderService;
import mx.sindelantal.test.model.response.JsonResponse;
import org.springframework.http.HttpStatus;

@RestApiController("apiOrders")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public JsonResponse create(@RequestBody Order order) {
        JsonResponse result = new JsonResponse();
        orderService.create(order);
        result.setStatus(HttpStatus.CREATED);
    	result.setBinaryStatus("success");
    	result.setMessage("Order succesfully created");
        return result;

    }
    
}
