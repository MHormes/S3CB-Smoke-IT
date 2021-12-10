package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.service.SubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@ActiveProfiles("test")
@SpringBootTest
class SubscriptionServiceFakeTest {

    @Autowired
    SubscriptionService subscriptionService;


    @Test
    void createSubscriptionSuccessful(){
        SubscriptionModel sub = new SubscriptionModel("1", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(sub);

        Assertions.assertEquals(sub, subscriptionService.getSubscriptionById(sub.getId()));
        Assertions.assertNotNull(subscriptionService.getActiveOrderBySubscriptionId(1L));
    }

    @Test
    void getAllActiveSubscriptions(){
        SubscriptionModel sub1 = new SubscriptionModel("2", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        SubscriptionModel sub2 = new SubscriptionModel("2", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");

        subscriptionService.createSubscription(sub1);
        subscriptionService.createSubscription(sub2);

        //Set order as shipped for sub 2. Only 1 amount bought means sub is no longer active
        subscriptionService.setOrderAsShipped(subscriptionService.getActiveOrderBySubscriptionId(3L).getId());


        Assertions.assertEquals(sub1, subscriptionService.getActiveSubscriptions("2").get(0));
        Assertions.assertEquals(1, subscriptionService.getActiveSubscriptions("2").size());
    }

    //TEST NEEDS CONSTANT UPDATE TO KEEP SUCCEEDING
    @Test
    void createOrderSuccessfulTest(){
        SubscriptionModel sub = new SubscriptionModel("1", 1L, 3, 2, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(sub);

        LocalDate date = LocalDate.now();
        //First order for sub is already made in create subscription method. Here we get it to test the date
        OrderModel orderModel = subscriptionService.getActiveOrderBySubscriptionId(1L);
        //Check if date is set correctly
        Assertions.assertEquals(date.with(TemporalAdjusters.firstDayOfNextMonth()), orderModel.getDeliverDate());

        //Create order for same subscription. Frequency is 2, new date should be 3 months from now. (Jan 1st box 1, March 1st box 2)
        OrderModel orderModel2 = subscriptionService.createOrder(1L);
        //Check if date is set correctly
        Assertions.assertEquals(LocalDate.of(2022, 3, 1), orderModel2.getDeliverDate());


    }
}
