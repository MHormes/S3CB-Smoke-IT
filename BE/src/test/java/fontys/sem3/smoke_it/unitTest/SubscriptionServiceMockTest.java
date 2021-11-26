package fontys.sem3.smoke_it.unitTest;


import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceSubscriptions;
import fontys.sem3.smoke_it.service.SubscriptionService;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class SubscriptionServiceMockTest {

    @Mock
    IDataSourceSubscriptions datasource;

    @Autowired
    ISubscriptionService subscriptionService;

    SubscriptionModel model1;
    SubscriptionModel model2;

    @BeforeEach
    void setUp(){
        List<SubscriptionModel> mockList = new ArrayList<>();
        model1 = new SubscriptionModel("1", 0L, 2, 1, "Maarten", "Email@gmail.com", "someStreet 4", "6045EA", "Roermond");
        model2 = new SubscriptionModel("1", 1L, 0, 2, "Maarten", "Email@gmail.com", "someStreet 4", "6045EA", "Roermond");
        mockList.add(model1);
        mockList.add(model2);

        when(datasource.getSubscriptionsByBoxId("1")).thenReturn(mockList);

    }
}
