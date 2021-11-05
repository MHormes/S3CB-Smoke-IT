package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;

public interface IUserService {

    Boolean attemptLogin(String username, String password);

    UserModel getUserModel(String username);

    Boolean createUserModel(UserModel userModel);


}
