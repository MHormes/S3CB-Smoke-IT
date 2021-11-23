package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISubscriptionRepository extends JpaRepository<SubscriptionModel, Long> {
    @Query(value = "SELECT new fontys.sem3.smoke_it.model.GroupedOrders(b.name, b.id ,COUNT(s.boxId)) FROM SubscriptionModel as s inner join BoxModel as b on s.boxId = b.id group by b.name")
    List<GroupedOrders> getOrderModelsGrouped();

    List<SubscriptionModel> getSubscriptionModelsByBoxId(String id);

    SubscriptionModel getSubscriptionModelById(Long id);
}