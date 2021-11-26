package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.OrderDTO;
import fontys.sem3.smoke_it.model.OrderModel;
import fontys.sem3.smoke_it.model.SubscriptionDTO;
import fontys.sem3.smoke_it.model.SubscriptionModel;

public class SubscriptionModelConverter {

    public SubscriptionModel convertDTOToModel(SubscriptionDTO s){
        return new SubscriptionModel(s.getBoxId(), s.getUserId(), s.getAmountBought(), s.getFrequency(), s.getName(), s.getEmail(), s.getAddress(), s.getPostal(), s.getCity());
    }

    public OrderDTO mergeOrderAndSubscription(OrderModel o, SubscriptionModel s){
        return new OrderDTO(o.getId(), s.getId(), s.getBoxId(), s.getUserID(), s.getAmountBought(), s.getAmountLeft(), s.getFrequency(), s.getEmail(), s.getName(), s.getAddress(), s.getPostal(), s.getCity(), o.getPacked(), o.getShipped(), o.getDeliverDate());
    }
}
