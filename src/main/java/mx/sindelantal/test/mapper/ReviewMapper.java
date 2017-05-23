package mx.sindelantal.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;

import org.springframework.jdbc.core.RowMapper;

import mx.sindelantal.test.model.Review;

public class ReviewMapper implements RowMapper<Review>{
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		review.setId(rs.getLong("id"));
		review.setRating(new BigDecimal(rs.getDouble("rating")));
		review.setComment(rs.getString("comment"));
		review.setOrderId(rs.getLong("order_id"));
        return review;
   }
}
