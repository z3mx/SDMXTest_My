package mx.sindelantal.test.model.filter;

import java.math.BigDecimal;
import java.util.Date;

public class RestaurantFilter {
    private String sort;
    private Integer page;
    private Integer pageSize;
    private String nameEquals;
    private String nameLike;
    private BigDecimal ratingLow;
    private BigDecimal ratingHigh;
    private Date openSinceLow;
    private Date openSinceHigh;
	
    public void setSort(String sort){
        this.sort = sort;
    }
    public String getSort(){
        return sort;
    }

    public void setPage(Integer page){
        this.page = page;
    }
    public Integer getPage(){
        return page;
    }

    public void setPageSize(Integer pageSize){
        this.pageSize = pageSize;
    }
    public Integer getPageSize(){
        return pageSize;
    }

    public void setNameEquals(String nameEquals){
        this.nameEquals = nameEquals;
    }
    public String getNameEquals(){
        return nameEquals;
    }

    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }
    public String getNameLike(){
        return nameLike;
    }

    public void setRatingLow(BigDecimal ratingLow){
        this.ratingLow = ratingLow;
    }
    public BigDecimal getRatingLow(){
        return ratingLow;
    }

    public void setRatingHigh(BigDecimal ratingHigh){
        this.ratingHigh = ratingHigh;
    }
    public BigDecimal getRatingHigh(){
        return ratingHigh;
    }

    public void setOpenSinceLow(Date openSinceLow){
        this.openSinceLow = openSinceLow;
    }
    public Date getOpenSinceLow(){
        return openSinceLow;
    }

    public void setOpenSinceHigh(Date openSinceHigh){
        this.openSinceHigh = openSinceHigh;
    }
    public Date getOpenSinceHigh(){
        return openSinceHigh;
    }
}
