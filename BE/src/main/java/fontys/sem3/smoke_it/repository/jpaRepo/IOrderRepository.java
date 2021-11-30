package fontys.sem3.smoke_it.repository.jpaRepo;

import fontys.sem3.smoke_it.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IOrderRepository extends JpaRepository<OrderModel, Long> {
    OrderModel getOrderModelById(Long id);

    List<OrderModel> getOrdersModelBySubscriptionId(Long subscriptionId);
}
