package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name="Boxes")
public class BoxModel {

    @Id
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="basePrice")
    private double basePrice;
    @Column(name="content")
    private String content;
    @Column(name="description")
    private String description;
    @Column(name="imagePath")
    private String imagePath;

    public BoxModel(){

    }

    public BoxModel(String id, String name, double basePrice, String content, String description, String imagePath){
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.content = content;
        this.description = description;
        this.imagePath = imagePath;
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
        return Objects.hash(id);
    }
}
