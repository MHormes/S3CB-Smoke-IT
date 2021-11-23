package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IOrderRepository extends JpaRepository<OrderModel, Long> {
    OrderModel getOrderModelById(Long id);

    OrderModel getOrderModelBySubscriptionId(Long subscriptionId);
}
