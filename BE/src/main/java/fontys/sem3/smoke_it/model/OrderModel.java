package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="orders")
public class OrderModel {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="boxId")
    private String boxId;
    @Column(name="userId")
    private Long userID;
    @Column(name="name")
    private String name;
    @Column(name="Address")
    private String address;
    @Column(name="postal")
    private String postal;
    @Column(name="city")
    private String city;


    public OrderModel(){

    }

    public OrderModel(String boxId, Long userID, String name, String address, String postal, String city){
        this.boxId = boxId;
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.city = city;
    }

}
