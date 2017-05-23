package mx.sindelantal.test.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
    private static final long serialVersionUID = -553941675801668465L;
    private Long id;
    private Date placed;
    private OrderStatus status;
    private Long restaurantId;
    private Restaurant restaurant;
    
    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
    
    public void setPlaced(Date placed){
        this.placed = placed;
    }
    public Date getPlaced(){
        return placed;
    }
    
    public void setStatus(OrderStatus status){
        this.status = status;
    }
    public OrderStatus getStatus(){
        return status;
    }
    
    public void setRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    public Restaurant getRestaurant(){
        return restaurant;
    }
    public void setRestaurantId(Long restaurantId){
        this.restaurantId = restaurantId;
    }
    public Long getRestaurantId(){
        return restaurantId;
    }
}
