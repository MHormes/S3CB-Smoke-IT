package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;

public interface IUserService {

    public Boolean attemptLogin(String username, String password);

    public UserModel getUserModel(String username);
}
