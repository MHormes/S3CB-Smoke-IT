package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String boxId;
    private Long userId;
    private int amount;
    private int frequency;
    private String email;
    private String name;
    private String address;
    private String postal;
    private String city;
}
