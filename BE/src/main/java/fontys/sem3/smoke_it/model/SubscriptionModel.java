package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="subscription")
public class SubscriptionModel {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="boxId")
    private String boxId;
    @Column(name="userId")
    private Long userID;
    @Column(name="amountBought")
    private int amountBought;
    @Column(name="amountLeft")
    private int amountLeft;
    @Column(name="frequency")
    private int frequency;
    @Column(name="totalCost")
    private double totalCost;
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


    public SubscriptionModel(){
    }

    public SubscriptionModel(String boxId, Long userID, int amountBought, int frequency, double totalCost, String name, String email, String address, String postal, String city){
        this.boxId = boxId;
        this.userID = userID;
        this.amountBought = amountBought;
        this.amountLeft = amountBought;
        this.frequency = frequency;
        this.totalCost = totalCost;
        this.name = name;
        this.email = email;
        this.address = address;
        this.postal = postal;
        this.city = city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionModel orderModel = (SubscriptionModel) o;
        return Objects.equals(hashCode(), orderModel.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
