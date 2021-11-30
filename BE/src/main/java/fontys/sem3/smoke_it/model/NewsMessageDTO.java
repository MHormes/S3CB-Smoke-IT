package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewsMessageDTO {

    private Long id;
    private String title;
    private String text;
    private LocalDate postDate;

    public NewsMessageDTO(Long id, String title, String text){
        this.id = id;
        this.title = title;
        this.text = text;
    }
}
