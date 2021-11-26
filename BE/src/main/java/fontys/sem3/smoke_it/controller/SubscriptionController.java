package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.*;
import fontys.sem3.smoke_it.model.modelconverters.SubscriptionModelConverter;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    ISubscriptionService subscriptionService;
    SubscriptionModelConverter modelConverter;

    public SubscriptionController() {
        this.modelConverter = new SubscriptionModelConverter();
    }

    @PostMapping("/create")
    public ResponseEntity<SubscriptionModel> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) throws MessagingException, IOException {
        SubscriptionModel subscription = modelConverter.convertDTOToModel(subscriptionDTO);
        if (subscriptionService.createSubscription(subscription)) {
            //Email needs later fix
            //orderService.sendEmail();
            return ResponseEntity.ok().body(subscription);
        }
        String entity = "Order was not completed";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SubscriptionModel>> getSubscriptionsForUserId(@PathVariable(value = "id")String id){
            List<SubscriptionModel> subList = subscriptionService.getSubscriptionsForUserId(Long.parseLong(id));
            return ResponseEntity.ok().body(subList);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(value = "id") String id) {
        OrderModel orderModel = subscriptionService.getOrder(Long.parseLong(id));
        if (orderModel != null) {
            SubscriptionModel subscriptionModel = subscriptionService.getSubscriptionById(orderModel.getSubscriptionId());
            if (subscriptionModel != null) {
                OrderDTO orderDTO = modelConverter.mergeOrderAndSubscription(orderModel, subscriptionModel);
                return ResponseEntity.ok().body(orderDTO);
            } else {
                String entity = "No subscription found for supplied order id: " + id;
                return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
            }
        } else {
            String entity = "No order found with supplied id: " + id;
            return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/grouped")
    public ResponseEntity<List<GroupedOrders>> getAllOrdersGrouped() {
        List<GroupedOrders> ordersGrouped = subscriptionService.getAllOrdersGrouped();
        return ResponseEntity.ok().body(ordersGrouped);
    }

    @GetMapping("/grouped/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByBoxId(@PathVariable(value = "id") String id) {
        List<OrderDTO> returnList = new ArrayList<>();
        List<SubscriptionModel> activeSubscriptions = subscriptionService.getActiveSubscriptions(id);
        for (SubscriptionModel s : activeSubscriptions) {
            OrderModel order = subscriptionService.getActiveOrderBySubscriptionId(s.getId());
            if(order != null){
                OrderDTO orderDTO = modelConverter.mergeOrderAndSubscription(order, s);
                returnList.add(orderDTO);
            }
        }
        Collections.sort(returnList, new Comparator<OrderDTO>() {
            @Override
            public int compare(OrderDTO o1, OrderDTO o2) {
                return o1.getDeliveryDate().compareTo(o2.getDeliveryDate());
            }
        });
        return ResponseEntity.ok().body(returnList);
    }

    @PutMapping("/orders/pack/{id}")
    public ResponseEntity<OrderDTO> toggleOrderPacked(@PathVariable(value = "id")String id){
        subscriptionService.toggleOrderPacked(Long.parseLong(id));
        return this.getOrder(id);
    }

    @PutMapping("/orders/send/{id}")
    public ResponseEntity setOrderAsShipped(@PathVariable(value="id")String id){
        if(subscriptionService.setOrderAsShipped(Long.parseLong(id)) != null){
            return this.getOrder(id);
        }
        String entity = "It seems like the order has already been shipped";
        return new ResponseEntity(entity, HttpStatus.NOT_ACCEPTABLE);
    }

}
