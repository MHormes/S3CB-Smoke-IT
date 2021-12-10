package fontys.sem3.smoke_it.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "User")
public class UserModel {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;

    public UserModel() {
    }

    public UserModel(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(hashCode(), userModel.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
