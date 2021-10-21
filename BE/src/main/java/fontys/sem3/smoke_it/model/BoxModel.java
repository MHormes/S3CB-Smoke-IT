package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name="Boxes")
public class BoxModel {

    @Id
    private String ID;
    @Column(name="name")
    private String name;
    @Column(name="basePrice")
    private double basePrice;
    @Column(name="content")
    private String content;
    @Column(name="description")
    private String description;
    //PHOTO??????????????????/

    public BoxModel(){

    }

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
