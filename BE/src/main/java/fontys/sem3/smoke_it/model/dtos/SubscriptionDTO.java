package fontys.sem3.smoke_it.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDTO {

    private Long subscriptionId;
    private String boxId;
    private Long userId;
    private int amountBought;
    private int amountLeft;
    private int frequency;
    private double totalCost;
    private String email;
    private String name;
    private String address;
    private String postal;
    private String city;
}
