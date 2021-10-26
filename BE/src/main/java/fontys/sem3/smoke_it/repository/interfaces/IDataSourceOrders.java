package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.OrderModel;

public interface IDataSourceOrders{

    Boolean createOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);
}
