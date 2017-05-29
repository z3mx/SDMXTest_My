package mx.sindelantal.test.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import mx.sindelantal.test.model.Restaurant;
import mx.sindelantal.test.dao.RestaurantDAO;
import mx.sindelantal.test.dao.BaseDAO;
import mx.sindelantal.test.mapper.RestaurantMapper;
import mx.sindelantal.test.model.filter.RestaurantFilter;

@Repository
public class RestaurantDAOImpl extends BaseDAO implements RestaurantDAO{

    public void create(Restaurant restaurant) {
        Map<String, Object> params = new HashMap<String, Object>();
        if(restaurant.getOpenSince() == null){
            restaurant.setOpenSince(new Date());
        }
        String SQL = "insert into restaurant (name, open_since) values (:name, :open_since)";
        params.put("name", restaurant.getName());
        params.put("open_since", restaurant.getOpenSince());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public Restaurant getRestaurant(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from restaurant where id = :id";
        params.put("id", id);
        Restaurant restaurant = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(SQL, 
            params, new RestaurantMapper());
        return restaurant;
    }

    public Restaurant getRestaurantByName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from restaurant where name = :name";
        params.put("name", name);
        Restaurant restaurant = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(SQL, 
            params, new RestaurantMapper());
        return restaurant;
    }

    public List<Restaurant> listRestaurants() {
        String SQL = "select r.id, r.name, r.open_since, sum(ro.rating)/count(ro.id) as rating"
                + " from  restaurant r"
                + " left join delivery_order o on o.restaurant_id = r.id"
                + " left join review_order ro on ro.order_id = o.id where 1=1"
                + " group by r.id";
        List <Restaurant> restaurants = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new RestaurantMapper());
        return restaurants;
    }
    
    public List<Restaurant> listRestaurantsRating() {
        String SQL = "select r.id, r.name, r.open_since, sum(ro.rating)/count(ro.id) as rating"
                + " from  restaurant r"
                + " left join delivery_order o on o.restaurant_id = r.id"
                + " left join review_order ro on ro.order_id = o.id where 1=1"
                + " group by r.id"
                + " order by sum(ro.rating)/count(ro.id) DESC";
        List <Restaurant> restaurants = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new RestaurantMapper());
        return restaurants;
    }
    
    public List<Restaurant> listRestaurantsRatingCancelled() {
    	List <Restaurant> restaurants = new ArrayList<Restaurant>();
    	List <Restaurant> restaurantsRating = this.listRestaurantsRating();
        String SQL = "select  r.*,round(sum(o.order_status='cancelled') * (100/count(o.id)),2) as rating from restaurant r "
        		+ "	left join delivery_order o on o.restaurant_id = r.id"
        		+ " where r.id = restaurant_id"
        		+ " group by r.id";
        List <Restaurant> restaurantsCancelled = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new RestaurantMapper());
        for(Restaurant rR :restaurantsRating){
        	for(Restaurant rC:restaurantsCancelled){
        		if(rR.getName().equals(rC.getName())){
        			double rating = rR.getRating().doubleValue();
        			double cancelled = rC.getRating().doubleValue();
        			double newRating = (rating)-((rating/100)*cancelled);
        			rR.setRating(new BigDecimal(newRating));
        			restaurants.add(rR);
        			break;
        		}
        	}
        }
        return restaurants;
    }

    public List<Restaurant> listRestaurantsWithOrders(){
        String SQL = "select * from restaurant";
        List <Restaurant> restaurants = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new RestaurantMapper());
        return restaurants;
    }
    
    public List<Restaurant> searchRestaurants(RestaurantFilter restaurantFilter){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select r.id, r.name, sum(ro.rating)/count(ro.id) as rating, r.open_since"
        			+" from restaurant r"
        			+" left join delivery_order o on o.restaurant_id = r.id "
        			+" left join review_order ro on ro.order_id = o.id where 1=1 ";
        if(restaurantFilter.getNameLike() != null && !restaurantFilter.getNameLike().isEmpty()){
            SQL +=" and name like :name ";
            
            params.put("name", "%"+restaurantFilter.getNameLike()+"%");
        }
        SQL	+=" group by r.id";
        List <Restaurant> restaurants = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL,
            params,
            new RestaurantMapper());
        return restaurants;
    }

    public void delete(Long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "delete from restaurant where id = :id";
        params.put("id", id);
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public void update(Restaurant restaurant){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "update restaurant set name = :name, where id = :id";
        params.put("name", restaurant.getName());
        params.put("id", restaurant.getId());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public Restaurant findByName(String name) throws EmptyResultDataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from restaurant where name = :name";
        params.put("name", name);
        Restaurant restaurant = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(SQL, 
            params, new RestaurantMapper());
        return restaurant;
    }
}
