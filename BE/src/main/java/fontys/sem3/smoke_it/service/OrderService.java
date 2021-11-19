package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.repository.DataSourceOrders;
import fontys.sem3.smoke_it.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class OrderService implements IOrderService {

    @Autowired
    DataSourceOrders dataSource;

    @Override
    public Boolean createOrder(OrderModel orderModel) {
        return dataSource.createOrder(orderModel);
    }

    @Override
    public void sendEmail() throws AddressException, MessagingException, IOException {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("maarten.hormes@gmail.com", "8nx$3&9H3vpi");
//            }
//        });
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("maarten.hormes@gmail.com", false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("maarten.hormes@gmail.com"));
//        msg.setSubject("Smoke-it order confirmation");
//        msg.setContent("Hello Maarten, Thank you for your order", "text/html");
//        msg.setSentDate(new Date());
//
//        Transport.send(msg);
    }

    @Override
    public OrderModel getOrder(Long id) {
        return dataSource.getOrder(id);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return dataSource.getAllOrders();
    }

    @Override
    public List<GroupedOrders> getAllOrdersGrouped() {
        return dataSource.getAllOrdersGrouped();
    }

    @Override
    public List<OrderModel> getOrdersForBoxId(String boxId) {
        return dataSource.getOrdersByBoxId(boxId);
    }
}
