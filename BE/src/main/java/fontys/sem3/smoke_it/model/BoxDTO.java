package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BoxDTO {

    private String ID;
    private String name;
    private double basePrice;
    private String content;
    private String description;
    private byte[] image;
    private MultipartFile imageFile;

    public BoxDTO(String ID, String name, double basePrice, String content, String description){
        setID(ID);
        setName(name);
        setBasePrice(basePrice);
        setContent(content);
        setDescription(description);
    }
}
