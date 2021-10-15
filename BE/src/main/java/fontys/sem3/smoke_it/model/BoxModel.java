package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class BoxModel {

    private String ID;
    private String name;
    private double basePrice;
    private String content;
    private String description;
    //PHOTO??????????????????/

    public BoxModel(String ID, String name, double basePrice, String content, String description){
        this.ID = ID;
        this.name = name;
        this.basePrice = basePrice;
        this.content = content;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoxModel boxModel = (BoxModel) o;
        return Objects.equals(hashCode(), boxModel.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
