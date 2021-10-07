package fontys.sem3.smoke_it.model;

import java.util.Objects;

public class UserModel {

    private String username;
    private String password;
    private Boolean admin;

    public UserModel(String username, String password, Boolean admin){
            this.username = username;
            this.password = password;
            this.admin = admin;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    private void setAdmin(Boolean admin){
        this.admin = admin;
    }

    public Boolean getAdmin(){
        return this.admin;
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
