package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;

import java.util.List;

public interface IDataSourceOrders{

    Boolean createOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);

    List<OrderModel> getAllOrders();

    List<GroupedOrders> getAllOrdersGrouped();

    List<OrderModel> getOrdersByBoxId(String boxId);
}
