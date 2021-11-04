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
    @Column(name="amount")
    private int amount;
    @Column(name="frequency")
    private int frequency;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="postal")
    private String postal;
    @Column(name="city")
    private String city;


    public OrderModel(){

    }

    public OrderModel(String boxId, Long userID, int amount, int frequency, String name, String email,  String address, String postal, String city){
        this.boxId = boxId;
        this.userID = userID;
        this.amount = amount;
        this.frequency = frequency;
        this.name = name;
        this.email = email;
        this.address = address;
        this.postal = postal;
        this.city = city;
    }

}
