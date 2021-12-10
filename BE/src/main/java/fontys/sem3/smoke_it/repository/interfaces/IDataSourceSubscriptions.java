package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;

import java.time.LocalDate;
import java.util.List;

public interface IDataSourceSubscriptions {

    SubscriptionModel createSubscription(SubscriptionModel orderModel);

    SubscriptionModel getSubscriptionById(Long id);

    List<SubscriptionModel> getSubscriptionsByBoxId(String id);

    List<SubscriptionModel> getSubscriptionsByUserId(Long id);

    void decreaseSubscriptionAmount(Long id);

    OrderModel createOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);

    List<GroupedOrders> getAllOrdersGrouped();

    List<OrderModel> getOrdersBySubscriptionId(Long subscriptionId);

    void toggleOrderPacked(Long id);

    OrderModel setOrderAsShipped(Long id);
}
