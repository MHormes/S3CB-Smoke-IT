package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.OrderModel;

public interface IOrderService {

    Boolean createOrder(OrderModel orderModel);

    OrderModel getOrder(Long id);
}
