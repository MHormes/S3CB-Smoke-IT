package fontys.sem3.smoke_it.model;

public class UserDTO {

    private String username;
    private Boolean admin;

    public UserDTO(String username, Boolean admin){
        setUsername(username);
        setAdmin(admin);
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setAdmin(Boolean admin){
        this.admin = admin;
    }

    public Boolean getAdmin(){
        return this.admin;
    }

}
