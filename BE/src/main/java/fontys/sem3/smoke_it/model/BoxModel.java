package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
    @Lob
    @Column(name="image")
    private byte[] image;

    public BoxModel(){

    }

    public BoxModel(String ID, String name, double basePrice, String content, String description, byte[] image){
        this.ID = ID;
        this.name = name;
        this.basePrice = basePrice;
        this.content = content;
        this.description = description;
        this.image = image;
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
