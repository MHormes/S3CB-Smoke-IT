package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;

import java.time.LocalDate;
import java.util.List;

public interface ISubscriptionService {

    Boolean createSubscription(SubscriptionModel orderModel);

    SubscriptionModel getSubscriptionById(Long id);

    List<SubscriptionModel> getActiveSubscriptions(String id);
//
//    void sendEmail() throws AddressException, MessagingException, IOException;

    void createOrder(Long subscriptionId);

    LocalDate calculateNextOrderDate();

    OrderModel getOrder(Long id);

    List<GroupedOrders> getAllOrdersGrouped();

    OrderModel getOrderBySubscriptionId(Long subscriptionID);

    void setOrderAsPacked(Long id);

    void setOrderAsShipped(Long id);
}
