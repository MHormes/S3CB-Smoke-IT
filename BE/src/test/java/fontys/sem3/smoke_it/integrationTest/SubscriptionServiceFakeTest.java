package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.service.SubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class SubscriptionServiceFakeTest {

    @Autowired
    SubscriptionService subscriptionService;



    @Test
    void createSubscriptionSuccessful(){
        SubscriptionModel sub = new SubscriptionModel("1", 1L, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(sub);

        Assertions.assertEquals(sub, subscriptionService.getSubscriptionById(sub.getId()));
        Assertions.assertNotNull(subscriptionService.getActiveOrderBySubscriptionId(1L));
    }

    @Test
    void getAllActiveSubscriptions(){
        SubscriptionModel sub1 = new SubscriptionModel("1", 1L, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        SubscriptionModel sub2 = new SubscriptionModel("1", 1L, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");

        subscriptionService.createSubscription(sub1);
        subscriptionService.createSubscription(sub2);

        subscriptionService.setOrderAsShipped(subscriptionService.getActiveOrderBySubscriptionId(2L).getId());


        Assertions.assertEquals(subscriptionService.getActiveSubscriptions("1").get(0), sub1);
        Assertions.assertEquals(1, subscriptionService.getActiveSubscriptions("1").size());
    }
}
