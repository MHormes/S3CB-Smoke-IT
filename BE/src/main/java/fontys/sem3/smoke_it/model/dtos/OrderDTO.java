package fontys.sem3.smoke_it.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private Long subscriptionId;
    private String boxId;
    private Long userId;
    private int amountBought;
    private int amountLeft;
    private int frequency;
    private String email;
    private String name;
    private String address;
    private String postal;
    private String city;
    private Boolean packed;
    private Boolean shipped;
    private LocalDate deliveryDate;
}
