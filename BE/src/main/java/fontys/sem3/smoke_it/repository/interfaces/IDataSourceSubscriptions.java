package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;

import java.time.LocalDate;
import java.util.List;

public interface IDataSourceSubscriptions {

    void createSubscription(SubscriptionModel orderModel);

    SubscriptionModel getSubscriptionById(Long id);

    List<SubscriptionModel> getSubscriptionsByBoxId(String id);

    void decreaseSubscriptionAmount(Long id);

    void createOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);

    List<GroupedOrders> getAllOrdersGrouped();

    OrderModel getOrderBySubscriptionId(Long subscriptionId);

    void setOrderAsPacked(Long id);

    OrderModel setOrderAsShipped(Long id);
}
