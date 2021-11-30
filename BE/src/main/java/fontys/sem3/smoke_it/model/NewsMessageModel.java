package fontys.sem3.smoke_it.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="newsMessages")
public class NewsMessageModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="text")
    private String text;
    @Column(name="postDate")
    private LocalDate postDate;

    public NewsMessageModel(Long id, String title, String text, LocalDate postDate){
        this.id = id;
        this.title = title;
        this.text = text;
        this.postDate = postDate;
    }
}
