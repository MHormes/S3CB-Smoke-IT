package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.*;
import fontys.sem3.smoke_it.model.dtos.OrderDTO;
import fontys.sem3.smoke_it.model.dtos.SubscriptionDTO;
import fontys.sem3.smoke_it.model.modelconverters.SubscriptionModelConverter;
import fontys.sem3.smoke_it.service.interfaces.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionModel subscription = subscriptionService.createSubscription(modelConverter.convertDTOToModel(subscriptionDTO));
        if (subscription != null) {
            //Email needs later fix
            //orderService.sendEmail();
            SubscriptionDTO dto = modelConverter.convertModelToDTO(subscription);
            return ResponseEntity.ok().body(dto);
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

    @GetMapping("/ordersFor/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersBySubscriptionId(@PathVariable(value = "id")String id){
        List<OrderDTO> returnList = new ArrayList<>();
        List<OrderModel> orderList = subscriptionService.getAllOrdersBySubscriptionId(Long.valueOf(id));
        for(OrderModel o: orderList){
            returnList.add(modelConverter.convertOrderModelToDTO(o));
        }
        return ResponseEntity.ok().body(returnList);
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
        returnList.sort(new Comparator<OrderDTO>() {
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
    public ResponseEntity<OrderDTO> setOrderAsShipped(@PathVariable(value="id")String id){
        if(subscriptionService.setOrderAsShipped(Long.parseLong(id)) != null){
            return this.getOrder(id);
        }
        String entity = "It seems like the order has already been shipped";
        return new ResponseEntity(entity, HttpStatus.NOT_ACCEPTABLE);
    }

}
