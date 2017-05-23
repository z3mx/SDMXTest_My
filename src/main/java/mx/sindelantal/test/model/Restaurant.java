package mx.sindelantal.test.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.math.RoundingMode;

public class Restaurant implements Serializable{
    private static final long serialVersionUID = -3457215308496589594L;
    private Long id;
    private String name;
    private BigDecimal rating;
    private Date openSince;
    private List<Order> orders;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setRating(BigDecimal rating){
        this.rating = rating;
    }
    public BigDecimal getRating(){
        return rating.setScale(2,  RoundingMode.CEILING);
    }
    
    public void setOpenSince(Date openSince){
        this.openSince = openSince;
    }
    public Date getOpenSince(){
        return openSince;
    }
    
    public void setOrders(List<Order> orders){
        this.orders = orders;
    }
    public List<Order> getOrders(){
        return orders;
    }
    
}
