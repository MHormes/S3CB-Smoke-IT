package fontys.sem3.smoke_it.model.modelConverters;

import fontys.sem3.smoke_it.model.OrderDTO;
import fontys.sem3.smoke_it.model.OrderModel;

public class OrderModelConverter {

    public OrderModel convertDTOToModel(OrderDTO orderDTO){
        return new OrderModel(orderDTO.getBoxId(), orderDTO.getUserId(), orderDTO.getAmount(), orderDTO.getFrequency(), orderDTO.getName(), orderDTO.getEmail(), orderDTO.getAddress(), orderDTO.getPostal(), orderDTO.getCity());
    }
}
