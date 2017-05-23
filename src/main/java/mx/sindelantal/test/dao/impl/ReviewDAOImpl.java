package mx.sindelantal.test.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.sindelantal.test.dao.BaseDAO;
import mx.sindelantal.test.dao.ReviewDAO;
import mx.sindelantal.test.mapper.ReviewMapper;
import mx.sindelantal.test.model.Review;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ReviewDAOImpl extends BaseDAO implements ReviewDAO{

    public void create(Review review){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "insert into review_order (rating, comment, order_id) values (:rating, :comment, :order_id)";
        params.put("rating", review.getRating());
        params.put("comment", review.getComment()); 
        params.put("restaurant_id", review.getOrderId());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public Review getReview(Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from review_order where id = :id";
        params.put("id", id);
        Review review = new NamedParameterJdbcTemplate(jdbcTemplate).queryForObject(SQL, 
            params, new ReviewMapper());
        return review;
    }

    public List<Review> listReviews(){
    	String SQL = "select * from review_order";
        List <Review> reviews = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, new ReviewMapper());
        return reviews;
    }

    public List<Review> listReviewsByOrderId(Long orderId){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "select * from review_order where order_id = :order_id";
        params.put("order_id", orderId);
        List <Review> reviews = new NamedParameterJdbcTemplate(jdbcTemplate).query(SQL, params, new ReviewMapper());
        return reviews;	
    }

    public void delete(Long id){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "delete from review_order where id = :id";
        params.put("id", id);
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }

    public void update(Review review){
        Map<String, Object> params = new HashMap<String, Object>();
        String SQL = "update review_order set rating = :rating, comment = :comment where id = :id";
        params.put("rating", review.getRating());  
        params.put("comment", review.getComment());
        params.put("id", review.getId());
        new NamedParameterJdbcTemplate(jdbcTemplate).update(SQL, params);
        return;
    }
}
