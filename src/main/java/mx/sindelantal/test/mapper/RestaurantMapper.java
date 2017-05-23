package mx.sindelantal.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import mx.sindelantal.test.model.Restaurant;

public class RestaurantMapper implements RowMapper<Restaurant> {
   public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
      Restaurant restaurant = new Restaurant();
      restaurant.setId(rs.getLong("id"));
      restaurant.setName(rs.getString("name"));
      restaurant.setRating(rs.getBigDecimal("rating"));
      restaurant.setOpenSince(rs.getDate("open_since"));
      
      return restaurant;
   }
}