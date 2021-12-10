package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;

import java.time.LocalDate;
import java.util.List;

public interface ISubscriptionService {

    SubscriptionModel createSubscription(SubscriptionModel orderModel);

    SubscriptionModel getSubscriptionById(Long id);

    List<SubscriptionModel> getActiveSubscriptions(String id);

    List<SubscriptionModel> getSubscriptionsForUserId(Long id);

//    void sendEmail() throws AddressException, MessagingException, IOException;

    OrderModel createOrder(Long subscriptionId);


    OrderModel getOrder(Long id);

    List<GroupedOrders> getAllOrdersGrouped();

    OrderModel getActiveOrderBySubscriptionId(Long subscriptionID);

    List<OrderModel> getAllOrdersBySubscriptionId(Long subscriptionID);

    void toggleOrderPacked(Long id);

    OrderModel setOrderAsShipped(Long id);
}
