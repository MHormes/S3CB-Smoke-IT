package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import org.hibernate.criterion.Order;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.List;

public interface IOrderService {

    Boolean createOrder(OrderModel orderModel);

    void sendEmail() throws AddressException, MessagingException, IOException;

    OrderModel getOrder(Long id);

    List<OrderModel> getAllOrders();

    List<GroupedOrders> getAllOrdersGrouped();

    List<OrderModel> getOrdersForBoxId(String boxId);
}
