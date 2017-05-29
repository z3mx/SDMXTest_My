package mx.sindelantal.test.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mx.sindelantal.test.model.Restaurant;
import mx.sindelantal.test.dao.RestaurantDAO;
import mx.sindelantal.test.model.filter.RestaurantFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RestaurantService {
    private RestaurantDAO restaurantDAO;
    private OrderService orderService;
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    
    @Autowired
    public RestaurantService(RestaurantDAO restaurantDAO, OrderService orderService) {
        this.restaurantDAO = restaurantDAO;
        this.orderService = orderService;
    }
    public List<Restaurant> listRestaurants(){
        return restaurantDAO.listRestaurants();
    }
    public List<Restaurant> listRestaurantsRating(){
    	try {
    		return restaurantDAO.listRestaurantsRating();
    	}catch(Exception ex){
    		log.info("Error :"+ex);
    		return null;
    	}
    }
    public List<Restaurant> listRestaurantsRC(){
    	try {
    		List<Restaurant> ListRC =  restaurantDAO.listRestaurantsRatingCancelled();
    		  Collections.sort(ListRC, new Comparator<Restaurant>() {
    	            @Override
    	            public int compare(Restaurant o1, Restaurant o2) {
    	                return o2.getRating().compareTo(o1.getRating());
    	            }
    	        });
    		return ListRC;
    	}catch(Exception ex){
    		log.info("Error :"+ex);
    		return null;
    	}
    }
    public List<Restaurant> listRestaurantsWithOrders(){
        List<Restaurant> restaurants = restaurantDAO.listRestaurantsWithOrders();
        for(Restaurant restaurant : restaurants){
            restaurant.setOrders(orderService.listOrdersByRestaurantId(restaurant.getId()));
        }
        return restaurants;
    }
    public List<Restaurant> searchRestaurants(RestaurantFilter restaurantFilter){
    	return restaurantDAO.searchRestaurants(restaurantFilter);
    }
    public void create(Restaurant restaurant){
    	restaurantDAO.create(restaurant);
    }
    public Restaurant findByName(String name){
    	try {
    		return restaurantDAO.findByName(name);
    	}catch(EmptyResultDataAccessException e){
    		return null;
    	}
    }
}
