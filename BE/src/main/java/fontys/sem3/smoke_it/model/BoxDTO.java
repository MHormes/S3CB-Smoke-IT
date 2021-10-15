package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoxDTO {

    private String ID;
    private String name;
    private double basePrice;
    private String content;
    private String description;
    //PHOTO??????????????????/

    public BoxDTO(String ID, String name, double basePrice, String content, String description){
        setID(ID);
        setName(name);
        setBasePrice(basePrice);
        setContent(content);
        setDescription(description);
    }
}
