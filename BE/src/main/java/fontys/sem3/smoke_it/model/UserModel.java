package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserModel {

    private String username;
    private String password;
    private Boolean admin;

    public UserModel(String username, String password, Boolean admin){
            this.username = username;
            this.password = password;
            this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(username, userModel.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
