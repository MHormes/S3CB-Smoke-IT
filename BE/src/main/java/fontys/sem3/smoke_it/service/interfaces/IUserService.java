package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.UserModel;

public interface IUserService {
    UserModel getUserModel(String username);

    UserModel createUserModel(UserModel userModel);


}
