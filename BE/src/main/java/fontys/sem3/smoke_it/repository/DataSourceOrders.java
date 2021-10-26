package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceOrders;
import fontys.sem3.smoke_it.repository.interfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceOrders implements IDataSourceOrders {

    @Autowired
    IOrderRepository repo;

    @Override
    public Boolean createOrder(OrderModel orderModel) {
        repo.save(orderModel);
        return true;
    }

    @Override
    public OrderModel getOrder(Long id) {
        return repo.getOne(id);
    }
}
