package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.GroupedOrders;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceSubscriptions;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {


    IDataSourceSubscriptions dataSource;

    @Autowired
    public SubscriptionService(IDataSourceSubscriptions dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SubscriptionModel createSubscription(SubscriptionModel subscriptionModel) {
        SubscriptionModel model = dataSource.createSubscription(subscriptionModel);
        this.createOrder(subscriptionModel.getId(), LocalDate.now());
        dataSource.decreaseSubscriptionAmount(subscriptionModel.getId());
        return model;
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
            if (s.getAmountLeft() >= 0) {
                returnList.add(s);
            }
        }
        return returnList;
    }

    @Override
    public List<SubscriptionModel> getSubscriptionsForUserId(Long id) {
        return dataSource.getSubscriptionsByUserId(id);
    }

    @Override
    public OrderModel createOrder(Long subscriptionId, LocalDate currentDate) {
        LocalDate date = currentDate;
        SubscriptionModel subscriptionModel = dataSource.getSubscriptionById(subscriptionId);
        if (subscriptionModel.getAmountLeft() == subscriptionModel.getAmountBought()) {
            date = date.with(TemporalAdjusters.firstDayOfNextMonth());
        } else {
            int months = subscriptionModel.getFrequency();
            date = this.getActiveOrderBySubscriptionId(subscriptionId).getDeliverDate();
            for (int i = 0; i < months; i++) {
                date = date.with(TemporalAdjusters.firstDayOfNextMonth());
            }
        }
        OrderModel orderModel = new OrderModel(subscriptionId, date);
        return dataSource.createOrder(orderModel);
    }

    @Override
    public OrderModel getOrder(Long id) {
        return dataSource.getOrder(id);
    }

    @Override
    public List<GroupedOrders> getAllOrdersGrouped() {
        List<GroupedOrders> groupedOrders = dataSource.getAllOrdersGrouped();
        //Get all grouped orders. For each get all subscriptions
        for (GroupedOrders g : groupedOrders) {
            List<SubscriptionModel> modelList = this.getActiveSubscriptions(g.getBoxID());
            //For each subscription get the active order to check packed and shipped
            for (SubscriptionModel s : modelList) {
                OrderModel order = this.getActiveOrderBySubscriptionId(s.getId());
                //If order is not packed and needs to be sent within 7 days we update the pack flag
                if (!order.getPacked() && ChronoUnit.DAYS.between(LocalDate.now(), order.getDeliverDate()) <= 7) {
                    g.setPackFlag(g.getPackFlag() + 1);
                }
                //if order is packed but needs to be sent within 5 days we add to send flag
                else if (order.getPacked() && ChronoUnit.DAYS.between(LocalDate.now(), order.getDeliverDate()) <= 5) {
                    g.setShipFlag(g.getShipFlag() + 1);
                }
            }
        }
        return groupedOrders;
    }

    @Override
    public OrderModel getActiveOrderBySubscriptionId(Long subscriptionID) {
        List<OrderModel> orderList = dataSource.getOrdersBySubscriptionId(subscriptionID);
        for (OrderModel o : orderList) {
            if (!o.getShipped()) {
                return o;
            }
        }
        return null;
    }

    @Override
    public List<OrderModel> getAllOrdersBySubscriptionId(Long subscriptionID) {
        return dataSource.getOrdersBySubscriptionId(subscriptionID);
    }


    @Override
    public void toggleOrderPacked(Long id) {
        dataSource.toggleOrderPacked(id);
    }

    @Override
    public OrderModel setOrderAsShipped(Long id) {
        OrderModel ordermodel = dataSource.getOrder(id);
        if (ordermodel.getShipped() || !ordermodel.getPacked()) {
            return null;
        } else {
            SubscriptionModel subscriptionModel = dataSource.getSubscriptionById(ordermodel.getSubscriptionId());
            if (subscriptionModel.getAmountLeft() > 0) {
                this.createOrder(subscriptionModel.getId(), LocalDate.now());
            }
            dataSource.setOrderAsShipped(id);
            dataSource.decreaseSubscriptionAmount(subscriptionModel.getId());
            return ordermodel;
        }
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
