package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.OrderDTO;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.modelConverters.OrderModelConverter;
import fontys.sem3.smoke_it.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;
    OrderModelConverter modelConverter;

    public OrderController(){
        this.modelConverter = new OrderModelConverter();
    }

    @PostMapping("")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderDTO orderDTO){
        OrderModel orderModel = modelConverter.convertDTOToModel(orderDTO);
        if(orderService.createOrder(orderModel)){
            return ResponseEntity.ok().body(orderModel);
        }
        String entity = "Order was not completed";
        return new ResponseEntity(entity, HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable(value="id") String id){
        OrderModel orderModel = orderService.getOrder(Long.parseLong(id));
        if(orderModel != null){
            return ResponseEntity.ok().body(orderModel);
        }
        else{
            String entity = "No order found with supplied id: " + id;
            return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
        }
    }
}
