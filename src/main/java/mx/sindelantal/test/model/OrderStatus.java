package mx.sindelantal.test.model;

public enum OrderStatus {
    PLACED("placed"),
    ACCEPTED("accepted"),
    CANCELLED("cancelled"),
    REJECTED("rejected"),
    FINISHED("finished");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }
    
    public static OrderStatus getOrderStatus(String status){
        OrderStatus orderStatus = OrderStatus.PLACED;
        for (OrderStatus stat : OrderStatus.values()) {
            if(stat.value() == status){
                orderStatus = stat;
            }
        }
        return orderStatus;
    }

    public String value() {
        return status;
    }
}
