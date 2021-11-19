package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupedOrders {
    private String boxName;
    private String boxID;
    private Long amount;

    public GroupedOrders(String boxName, String boxID, Long amount){
        this.boxName = boxName;
        this.boxID = boxID;
        this.amount = amount;
    }
}
