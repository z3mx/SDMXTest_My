package mx.sindelantal.test.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.sindelantal.test.model.Order;
import mx.sindelantal.test.dao.OrderDAO;
import mx.sindelantal.test.model.OrderStatus;

@Service
public class OrderService {
    private OrderDAO orderDAO;
    
    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    
    public void create(Order order) {
        orderDAO.create(order);
    }
    
    public List<Order> listOrdersByRestaurantId(Long restaurantId){
        return orderDAO.listOrdersByRestaurantId(restaurantId);
    }
}
