package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.service.SubscriptionService;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class SubscriptionServiceFakeTest {

    @Autowired
    SubscriptionService subscriptionService;
    //Need a box service for adding a box to get ordersgrouped without failing
    @Autowired
    IBoxService boxService;

    @Test
    void createSubscriptionSuccessful() {
        SubscriptionModel sub = new SubscriptionModel("1", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(sub);

        Assertions.assertEquals(sub, subscriptionService.getSubscriptionById(sub.getId()));
        Assertions.assertNotNull(subscriptionService.getActiveOrderBySubscriptionId(1L));
    }

    @Test
    void getAllActiveSubscriptions() {
        SubscriptionModel sub1 = new SubscriptionModel("2", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        SubscriptionModel sub2 = new SubscriptionModel("2", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");

        subscriptionService.createSubscription(sub1);
        subscriptionService.createSubscription(sub2);

        //Set order as packed and shipped for sub 2. Only 1 amount bought means sub is no longer active
        subscriptionService.toggleOrderPacked(subscriptionService.getActiveOrderBySubscriptionId(sub2.getId()).getId());
        subscriptionService.setOrderAsShipped(subscriptionService.getActiveOrderBySubscriptionId(sub2.getId()).getId());


        Assertions.assertEquals(sub1, subscriptionService.getActiveSubscriptions("2").get(0));
        Assertions.assertEquals(1, subscriptionService.getActiveSubscriptions("2").size());
    }

    @Test
    void createOrderSuccessfulTest() {
        SubscriptionModel sub = new SubscriptionModel("1", 1L, 3, 2, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(sub);

        //First order for sub is already made in create subscription method. Here we get it to test the date
        OrderModel orderModel = subscriptionService.getActiveOrderBySubscriptionId(1L);
        //Check if date is set correctly (should be first of february)
        Assertions.assertEquals(LocalDate.of(2022, 2, 1), orderModel.getDeliverDate());

        //Create order for same subscription. Frequency is 2, new date should be 3 months from now. (Feb 1st box 1, April 1st box 2)
        OrderModel orderModel2 = subscriptionService.createOrder(sub.getId(), LocalDate.now());
        //Check if date is set correctly
        Assertions.assertEquals(LocalDate.of(2022, 4, 1), orderModel2.getDeliverDate());
    }

    @Test
    void getSubscriptionsForUserIdTest() {
        SubscriptionModel firstModel = new SubscriptionModel("1", 5L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(firstModel);
        subscriptionService.createSubscription(new SubscriptionModel("1", 2L, 1, 1, 1, "Miguel", "mellow@gmail.com", "address", "postal", "city"));

        List<SubscriptionModel> subModelList = subscriptionService.getSubscriptionsForUserId(5L);

        Assertions.assertEquals(1, subModelList.size());
        Assertions.assertEquals(firstModel, subModelList.get(0));
    }

    @Test
    void getOrdersGroupedTest() {
        //Create 2 times 2 subscriptions
        SubscriptionModel sub1 = new SubscriptionModel("4", 6L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        SubscriptionModel sub2 = new SubscriptionModel("4", 6L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");

        SubscriptionModel sub3 = new SubscriptionModel("5", 7L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        SubscriptionModel sub4 = new SubscriptionModel("5", 7L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");

        subscriptionService.createSubscription(sub1);
        subscriptionService.createSubscription(sub2);

        subscriptionService.createSubscription(sub3);
        subscriptionService.createSubscription(sub4);

        boxService.createBox(new BoxModel("4", "boxId4", 1.00, "testContent", "testDescription", "testPath"));
        boxService.createBox(new BoxModel("5", "boxId5", 1.00, "testContent", "testDescription", "testPath"));

        List<GroupedOrders> groupedOrders = subscriptionService.getAllOrdersGrouped();

        //2 subs per box means both boxes will have an amount of 2.
        Assertions.assertEquals(2, groupedOrders.get(0).getAmount());
    }

    @Test
    void getOrdersForSubscriptionIdTest() {
        SubscriptionModel subModel = new SubscriptionModel("1", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(subModel);

        OrderModel orderModel = subscriptionService.createOrder(subModel.getId(), LocalDate.now());

        List<OrderModel> ordersList = subscriptionService.getAllOrdersBySubscriptionId(subModel.getId());

        Assertions.assertEquals(2, ordersList.size());
        Assertions.assertEquals(orderModel.getId(), ordersList.get(1).getId());
    }

    @Test
    void toggleOrderPackedTest() {
        SubscriptionModel subModel = new SubscriptionModel("1", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(subModel);
        //Get the created order
        OrderModel order = subscriptionService.getActiveOrderBySubscriptionId(subModel.getId());
        //Toggle packed to true
        subscriptionService.toggleOrderPacked(order.getId());
        //Get the order again
        OrderModel packedOrder = subscriptionService.getOrder(order.getId());
        //Check if pack true
        Assertions.assertEquals(true, packedOrder.getPacked());

        //Toggle packed to false
        subscriptionService.toggleOrderPacked(order.getId());
        //Get the order again
        OrderModel unPackedOrder = subscriptionService.getOrder(order.getId());
        //Check if pack is false
        Assertions.assertEquals(false, unPackedOrder.getPacked());

    }

    @Test
    void setOrderAsShippedTest() {
        SubscriptionModel subModel = new SubscriptionModel("1", 1L, 2, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(subModel);
        //Get the created order
        OrderModel order = subscriptionService.getActiveOrderBySubscriptionId(subModel.getId());
        //First set order as packed
        subscriptionService.toggleOrderPacked(order.getId());
        //Set order as shipped
        subscriptionService.setOrderAsShipped(order.getId());

        //Get all orders for sub id to see if new order was created automatically.
        List<OrderModel> orderModelList = subscriptionService.getAllOrdersBySubscriptionId(subModel.getId());

        //Test if list size = 2
        Assertions.assertEquals(2, orderModelList.size());
        //Test if order has shipped status true
        Assertions.assertTrue(subscriptionService.getOrder(order.getId()).getShipped());
    }

    @Test
    void setOrderAsShippedOrderAlreadyShippedTest() {
        SubscriptionModel subModel = new SubscriptionModel("1", 1L, 1, 1, 1, "Maarten", "maarten@gmail.com", "address", "postal", "city");
        subscriptionService.createSubscription(subModel);
        //Get the created order
        OrderModel order = subscriptionService.getActiveOrderBySubscriptionId(subModel.getId());
        //Set order as shipped
        subscriptionService.setOrderAsShipped(order.getId());

        //Order was not packed, should still not be sent
        Assertions.assertFalse(order.getShipped());
    }

}
