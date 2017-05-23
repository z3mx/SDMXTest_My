package mx.sindelantal.test.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.sindelantal.test.dao.BaseDAO;
import mx.sindelantal.test.dao.OrderDAO;
import mx.sindelantal.test.model.Order;
import mx.sindelantal.test.model.OrderStatus;
import mx.sindelantal.test.mapper.OrderMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends BaseDAO implements OrderDAO{

    public void create(Order order){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "insert into delivery_order (placed, order_status, restaurant_id) values (:placed, :order_status, :restaurant_id)";
        params.put("placed", order.getPlaced());
        params.put("order_status", order.getStatus().value()); 
        params.put("restaurant_id", order.getRestaurantId());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public Order getOrder(Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from delivery_order where id = :id";
        params.put("id", id);
        Order order = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(SQL, 
            params, new OrderMapper());
        return order;
    }

    public List<Order> listOrders(){
        String SQL = "select * from delivery_order";
        List <Order> orders = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new OrderMapper());
        return orders;
    }

    public List<Order> listOrdersByRestaurantId(Long restaurantId){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from delivery_order where restaurant_id = :restaurant_id";
        params.put("restaurant_id", restaurantId);
        List <Order> orders = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, params, new OrderMapper());
        return orders;
    }

    public void delete(Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "delete from delivery_order where id = :id";
        params.put("id", id);
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public void update(Order order){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "update delivery_order set order_status = :order_status where id = :id";
        params.put("order_status", order.getStatus().value());
        params.put("order_status", order.getId());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }
}
