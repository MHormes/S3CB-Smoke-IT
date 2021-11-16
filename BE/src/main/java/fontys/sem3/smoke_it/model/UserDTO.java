package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String role;

    public UserDTO(String username, String role){
        setUsername(username);
        setRole(role);
    }
}
