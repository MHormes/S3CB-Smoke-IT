package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="orders")
public class OrderModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="subscriptionId")
    private Long subscriptionId;
    @Column(name="date")
    private LocalDate deliverDate;
    @Column(name="packed")
    private Boolean packed;
    @Column(name="shipped")
    private Boolean shipped;

    public OrderModel(){
    }

    public OrderModel(Long subscriptionId, LocalDate deliverDate){
        this.subscriptionId = subscriptionId;
        this.deliverDate = deliverDate;
        this.packed = false;
        this.shipped = false;
    }
}
