package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceOrders;
import fontys.sem3.smoke_it.repository.interfaces.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

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
        Optional<OrderModel> orderModel = repo.findById(id);
        return orderModel.orElse(null);
    }
}
