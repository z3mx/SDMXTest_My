package mx.sindelantal.test.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Review implements Serializable{
	private static final long serialVersionUID = 2140043105168194559L;
	private Long id;
	private BigDecimal rating;
	private String comment;
	private Long orderId;
	private Order order;
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	
	public void setRating(BigDecimal rating){
		this.rating = rating;
	}
	public BigDecimal getRating(){
		return rating;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return comment;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public Long getOrderId(){
		return orderId;
	}
	
	public void setOrder(Order order){
		this.order = order;
	}
	public Order getOrder(){
		return order;
	}

}
