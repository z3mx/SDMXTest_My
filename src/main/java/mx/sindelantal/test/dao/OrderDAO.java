package mx.sindelantal.test.dao;

import java.util.List;

import mx.sindelantal.test.model.Order;

public interface OrderDAO {
    /** 
      * This is the method to be used to create
      * a record in the order table.
    */
    public void create(Order order);
   
    /** 
      * This is the method to be used to list down
      * a record from the order table corresponding
      * to a passed order id.
    */
    public Order getOrder(Long id);
   
    /** 
      * This is the method to be used to list down
      * all the records from the order table.
    */
    public List<Order> listOrders();
    
    /** 
      * This is the method to be used to list down
      * all the records from the order table belonging
      * to a restaurant.
    */
    public List<Order> listOrdersByRestaurantId(Long restaurantId);
    
   
    /** 
      * This is the method to be used to delete
      * a record from the order table corresponding
      * to a passed order id.
    */
    public void delete(Long id);
   
    /** 
      * This is the method to be used to update
      * a record into the order table.
    */
    public void update(Order order);
}
