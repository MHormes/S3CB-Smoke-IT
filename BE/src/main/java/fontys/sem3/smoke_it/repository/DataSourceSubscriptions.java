package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceSubscriptions;
import fontys.sem3.smoke_it.repository.interfaces.IOrderRepository;
import fontys.sem3.smoke_it.repository.interfaces.ISubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataSourceSubscriptions implements IDataSourceSubscriptions {

    @Autowired
    ISubscriptionRepository subRepo;

    @Autowired
    IOrderRepository orderRepo;

    @Override
    public void createSubscription(SubscriptionModel subscriptionModel) {
        subRepo.save(subscriptionModel);
    }

    @Override
    public SubscriptionModel getSubscriptionById(Long id) {
        return subRepo.getSubscriptionModelById(id);
    }

    @Override
    public List<SubscriptionModel> getSubscriptionsByBoxId(String id) {
        return subRepo.getSubscriptionModelsByBoxId(id);
    }

    @Override
    public List<SubscriptionModel> getSubscriptionsByUserId(Long id) {
        return subRepo.getSubscriptionModelsByUserID(id);
    }

    @Override
    public void decreaseSubscriptionAmount(Long id) {
        SubscriptionModel subscriptionModel =  subRepo.getSubscriptionModelById(id);
        subscriptionModel.setAmountLeft(subscriptionModel.getAmountLeft() -1);
        subRepo.save(subscriptionModel);
    }


    @Override
    public void createOrder(OrderModel orderModel) {
        orderRepo.save(orderModel);
    }

    @Override
    public OrderModel getOrder(Long id) {
        return orderRepo.getOrderModelById(id);
    }

    @Override
    public List<GroupedOrders> getAllOrdersGrouped() {
        return subRepo.getOrderModelsGrouped();
    }


    @Override
    public List<OrderModel> getOrdersBySubscriptionId(Long subscriptionId) {
        return orderRepo.getOrdersModelBySubscriptionId(subscriptionId);
    }

    @Override
    public void toggleOrderPacked(Long id) {
        OrderModel orderModel = orderRepo.getOrderModelById(id);
        orderModel.setPacked(!orderModel.getPacked());
        orderRepo.save(orderModel);
    }

    @Override
    public OrderModel setOrderAsShipped(Long id) {
        OrderModel orderModel = orderRepo.getOrderModelById(id);
        orderModel.setShipped(true);
        orderRepo.save(orderModel);
        return orderModel;
    }
}
