package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private Boolean admin;

    public UserDTO(String username, Boolean admin){
        setUsername(username);
        setAdmin(admin);
    }
}
