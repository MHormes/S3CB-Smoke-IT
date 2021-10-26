package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class OrderDTO {

    private Long orderId;
    private String boxId;
    private Long userId;
    private String email;
    private String name;
    private String address;
    private String postal;
    private String city;
}
