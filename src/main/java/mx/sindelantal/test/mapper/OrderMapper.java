package mx.sindelantal.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import mx.sindelantal.test.model.Order;
import mx.sindelantal.test.model.OrderStatus;

public class OrderMapper implements RowMapper<Order>{
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setPlaced(rs.getDate("placed"));
        order.setStatus(OrderStatus.getOrderStatus(rs.getString("order_status")));
        order.setRestaurantId(rs.getLong("restaurant_id"));
        return order;
   }
}
