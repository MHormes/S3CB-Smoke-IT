package fontys.sem3.smoke_it.unitTest;


import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceSubscriptions;
import fontys.sem3.smoke_it.service.SubscriptionService;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionServiceUnitTest {

    @Mock
    IDataSourceSubscriptions dataSource;

    ISubscriptionService subscriptionService;

    SubscriptionModel model1;
    SubscriptionModel model2;

    OrderModel orderModel1;
    OrderModel orderModel2;

    @BeforeEach
    void setUp(){
        subscriptionService = new SubscriptionService(dataSource);

        List<SubscriptionModel> mockList = new ArrayList<>();
        //Create sub model and set amount lef to -1 for 'getActiveSubscriptionsForBoxIdTest'
        model1 = new SubscriptionModel("1", 0L, 1, 1, 1,"Maarten", "Email@gmail.com", "someStreet 4", "6045EA", "Roermond");
        model1.setAmountLeft(-1);
        //Create second sub model
        model2 = new SubscriptionModel("1", 1L, 2, 2, 2,"Maarten", "Email@gmail.com", "someStreet 4", "6045EA", "Roermond");
        mockList.add(model1);
        mockList.add(model2);
        when(dataSource.getSubscriptionsByBoxId("1")).thenReturn(mockList);
        when(dataSource.getSubscriptionById(1L)).thenReturn(model2);

        //Create mock list
        List<OrderModel> orderMockList = new ArrayList<>();
        //Create new order that is set send and packed
        orderModel1 = new OrderModel(1L, LocalDate.now());
        orderModel1.setShipped(true); orderModel1.setPacked(true);
        //Create brand-new order model
        orderModel2 = new OrderModel(1L, LocalDate.now());
        orderMockList.add(orderModel1);
        orderMockList.add(orderModel2);
        when(dataSource.getOrdersBySubscriptionId(1L)).thenReturn(orderMockList);
    }

    @Test
    void getActiveSubscriptionsForBoxIdTest(){
        List<SubscriptionModel> subscriptionModels = subscriptionService.getActiveSubscriptions("1");

        Assertions.assertEquals(1, subscriptionModels.size());
        Assertions.assertEquals(model2, subscriptionModels.get(0));
    }

    @Test
    void getActiveOrdersForSubscriptionId(){
        //Get the active order
        OrderModel returnModel = subscriptionService.getActiveOrderBySubscriptionId(1L);
        //See if the order returned is ordermodel2, since 1 is already send
        Assertions.assertEquals(orderModel2, returnModel);
    }

}
