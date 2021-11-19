package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IOrderRepository extends JpaRepository<OrderModel, Long> {
    @Query(value = "SELECT new fontys.sem3.smoke_it.model.GroupedOrders(b.name, b.id ,COUNT(o.boxId)) FROM OrderModel as o inner join BoxModel as b on o.boxId = b.id group by b.name")
    List<GroupedOrders> getOrderModelsGrouped();

    List<OrderModel> getOrderModelsByBoxId(String boxId);
}
