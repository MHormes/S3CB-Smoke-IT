package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.repository.DataSourceOrders;
import fontys.sem3.smoke_it.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    DataSourceOrders dataSource;

    @Override
    public Boolean createOrder(OrderModel orderModel) {
        return dataSource.createOrder(orderModel);
    }

    @Override
    public OrderModel getOrder(Long id) {
        return dataSource.getOrder(id);
    }
}
