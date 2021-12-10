package fontys.sem3.smoke_it.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModelDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}
