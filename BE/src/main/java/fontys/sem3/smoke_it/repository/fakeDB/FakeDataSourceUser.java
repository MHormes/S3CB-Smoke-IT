package fontys.sem3.smoke_it.repository.fakeDB;

import fontys.sem3.smoke_it.model.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FakeDataSourceUser implements IDataSourceUser {

    public List<UserModel> userModelList = new ArrayList<>();

    public FakeDataSourceUser(){
        userModelList.add(new UserModel("user", "user", false));
        userModelList.add(new UserModel("admin" , "admin", true));
    }

    @Override
    public Boolean attemptLogin(String username, String password) {
        for(UserModel user: userModelList){
            if(Objects.equals(user.getUsername(), username)) {
                return Objects.equals(user.getPassword(), password);
            }
        }
        return false;
    }

    @Override
    public UserModel getUserModel(String username) {
        for(UserModel user: userModelList){
            if(Objects.equals(username, user.getUsername())){
                return user;
            }
        }
        return null;
    }
}
