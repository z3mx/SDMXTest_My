package mx.sindelantal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.HashMap; 
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.sindelantal.test.model.Restaurant;
import mx.sindelantal.test.mapper.RestaurantMapper;
import mx.sindelantal.test.mapper.OrderMapper;
import mx.sindelantal.test.model.Order;
import mx.sindelantal.test.model.OrderStatus;


@SpringBootApplication
public class Application implements CommandLineRunner{
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");
        
        jdbcTemplate.execute("DROP TABLE IF EXISTS review_order");
        jdbcTemplate.execute("DROP TABLE IF EXISTS delivery_order");
        jdbcTemplate.execute("DROP TABLE IF EXISTS restaurant");
        
        jdbcTemplate.execute("CREATE TABLE restaurant " 
        					 + "(id INT NOT NULL AUTO_INCREMENT, "
                             + "name VARCHAR(100) NOT NULL, "
                             + "rating DECIMAL(3,2) NULL, "
                             + "open_since DATETIME NOT NULL, "
                             + "PRIMARY KEY(id))");
        
        jdbcTemplate.execute("CREATE TABLE delivery_order "
        					+ "(id INT NOT NULL AUTO_INCREMENT, "
        					+ "order_status VARCHAR(100) NOT NULL, "
        					+ "placed DATETIME NOT NULL, "
        					+ "restaurant_id INT NOT NULL, "
        					+ "PRIMARY KEY(id), "
        					+ "FOREIGN KEY (restaurant_id) REFERENCES restaurant(id))");
        
        jdbcTemplate.execute("CREATE TABLE review_order "
							+ "(id INT NOT NULL AUTO_INCREMENT, "
							+ "rating DECIMAL(3,2) NOT NULL, "
							+ "comment TEXT NULL, "
							+ "order_id INT NOT NULL, "
							+ "PRIMARY KEY(id), "
							+ "FOREIGN KEY (order_id) REFERENCES delivery_order(id))");


        
        
        List<String> restNames = Arrays.asList("La Polleria","La Taqueria","La Asaderia","La Veganeria","La Pizzeria","La Pescaderia","La Sushiteria");
        List<Object[]> restaurants = new ArrayList<Object[]>();
        
        Object[] rest = new Object[2];
        for(String name : restNames){
        	rest = new Object[2];
        	rest[0] = name;
        	rest[1] = new Date();
        	restaurants.add(rest);
        }

        jdbcTemplate.batchUpdate("INSERT INTO restaurant(name, open_since) VALUES (?,?)", restaurants);
        
        List<Object[]> orders = new ArrayList<Object[]>();
        
        Object[] order = new Object[3];
        Restaurant r;
        Random rand = new Random();
        for(int i=1; i<=35; i++){       
                r = jdbcTemplate.queryForObject(
                        "SELECT *  FROM restaurant WHERE name = ?", 
                        new Object[] { restNames.get(rand.nextInt(restNames.size())) }, 
                        new RestaurantMapper());
                
        	order = new Object[3];
        	order[0] = new Date();
        	order[1] = OrderStatus.FINISHED.value();
                order[2] = r.getId();
        	orders.add(order);
        }
        jdbcTemplate.batchUpdate("INSERT INTO delivery_order (placed, order_status, restaurant_id) values (?,?,?)", orders);
        
        List<Object[]> reviews = new ArrayList<Object[]>();
        
        Object[] review = new Object[3];
        List<Order> os;
        int  n;
        for(int i=1; i<=105; i++){
                r = jdbcTemplate.queryForObject(
                        "SELECT *  FROM restaurant WHERE name = ?", 
                        new Object[] { restNames.get(rand.nextInt(restNames.size())) }, 
                        new RestaurantMapper());
                
                os = jdbcTemplate.query(
                        "SELECT *  FROM delivery_order WHERE restaurant_id = ?", 
                        new Object[] { r.getId() }, 
                        new OrderMapper());
                if(!os.isEmpty()){
                    review = new Object[3];
                    n = rand.nextInt(5) + 1;
                    review[0] = new BigDecimal(n);
                    review[1] = "Comentario aleatorio "+i;
                    int index = rand.nextInt(os.size());
                    review[2] = os.get(index).getId();
                    reviews.add(review);
                }
        	
        }
        jdbcTemplate.batchUpdate("INSERT INTO review_order (rating, comment, order_id) values (?,?,?)", reviews);
    }

}