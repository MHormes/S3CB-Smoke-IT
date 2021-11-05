package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceFakeTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrderSuccessfully(){
        OrderModel order = new OrderModel("1", 1L, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        orderService.createOrder(order);

        Assertions.assertTrue(order.equals(orderService.getOrder(1L)));
    }


}
