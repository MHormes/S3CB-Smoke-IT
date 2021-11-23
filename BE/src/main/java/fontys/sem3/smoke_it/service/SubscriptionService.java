package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.repository.DataSourceSubscriptions;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    DataSourceSubscriptions dataSource;

    @Override
    public Boolean createSubscription(SubscriptionModel subscriptionModel) {
        dataSource.createSubscription(subscriptionModel);
        this.createOrder(subscriptionModel.getId());
        return true;
    }

    @Override
    public SubscriptionModel getSubscriptionById(Long id) {
        return dataSource.getSubscriptionById(id);
    }

    @Override
    public List<SubscriptionModel> getActiveSubscriptions(String id) {
        List<SubscriptionModel> returnList = new ArrayList<>();
        List<SubscriptionModel> subscriptionModels = dataSource.getSubscriptionsByBoxId(id);
        for (SubscriptionModel s : subscriptionModels) {
            if (s.getAmountLeft() != 0) {
                returnList.add(s);
            }
        }
        return returnList;
    }

    @Override
    public void createOrder(Long subscriptionId) {
        LocalDate date = LocalDate.now();
        SubscriptionModel subscriptionModel = dataSource.getSubscriptionById(subscriptionId);
        if(subscriptionModel.getAmountLeft() == subscriptionModel.getAmountBought()){
             date = date.with(TemporalAdjusters.firstDayOfNextMonth());
        }else{
            int months = subscriptionModel.getFrequency();
            for(int i = 0; i < months; i++){
                date = dataSource.getOrderBySubscriptionId(subscriptionId).getDeliverDate().with(TemporalAdjusters.firstDayOfNextMonth());
            }
        }
        OrderModel orderModel = new OrderModel(subscriptionId, date);
        dataSource.createOrder(orderModel);
    }

    @Override
    public LocalDate calculateNextOrderDate() {
        return null;
    }

    @Override
    public OrderModel getOrder(Long id) {
        return dataSource.getOrder(id);
    }

    @Override
    public List<GroupedOrders> getAllOrdersGrouped() {
        return dataSource.getAllOrdersGrouped();
    }

    @Override
    public OrderModel getOrderBySubscriptionId(Long subscriptionID) {
        return dataSource.getOrderBySubscriptionId(subscriptionID);
    }

    @Override
    public void setOrderAsPacked(Long id) {
        dataSource.setOrderAsPacked(id);
    }

    @Override
    public void setOrderAsShipped(Long id) {
        OrderModel ordermodel = dataSource.setOrderAsShipped(id);
        SubscriptionModel subscriptionModel = dataSource.getSubscriptionById(ordermodel.getSubscriptionId());
        if (subscriptionModel.getAmountLeft() > 1) {
            this.createOrder(subscriptionModel.getId());
        }
        dataSource.decreaseSubscriptionAmount(subscriptionModel.getId());
    }

    //    @Override
//    public void sendEmail() throws AddressException, MessagingException, IOException {
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
//    }
}
