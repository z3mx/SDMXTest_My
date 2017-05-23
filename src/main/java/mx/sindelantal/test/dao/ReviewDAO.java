package mx.sindelantal.test.dao;

import java.util.List;

import mx.sindelantal.test.model.Review;

public interface ReviewDAO {
    /** 
     * This is the method to be used to create
     * a record in the review table.
    */
    public void create(Review review);
  
    /** 
     * This is the method to be used to list down
     * a record from the review table corresponding
     * to a passed review id.
    */
    public Review getReview(Long id);
  
    /** 
     * This is the method to be used to list down
     * all the records from the review table.
    */
    public List<Review> listReviews();
   
    /** 
     * This is the method to be used to list down
     * all the records from the review table belonging
     * to an order.
    */
    public List<Review> listReviewsByOrderId(Long orderId);
   
  
    /** 
     * This is the method to be used to delete
     * a record from the review table corresponding
     * to a passed review id.
    */
    public void delete(Long id);
  
    /** 
     * This is the method to be used to update
     * a record into the review table.
    */
    public void update(Review review);
}
