package fontys.sem3.smoke_it.model.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Setter
@Getter
public class BoxDTO {

    private String id;
    private String name;
    private double basePrice;
    private String content;
    private String description;
    private Path imagePath;
    private MultipartFile imageFile;

    public BoxDTO(String id, String name, double basePrice, String content, String description){
        setId(id);
        setName(name);
        setBasePrice(basePrice);
        setContent(content);
        setDescription(description);
    }
}
