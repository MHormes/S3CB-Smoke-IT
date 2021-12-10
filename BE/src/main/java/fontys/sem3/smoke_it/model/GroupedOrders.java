package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupedOrders {
    private String boxName;
    private String boxID;
    private Long amount;
    private int packFlag;
    private int shipFlag;

    public GroupedOrders(String boxName, String boxID, Long amount, int packFlag, int shipFlag){
        this.boxName = boxName;
        this.boxID = boxID;
        this.amount = amount;
        this.packFlag = packFlag;
        this.shipFlag = shipFlag;
    }
}
