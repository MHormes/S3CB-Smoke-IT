package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.UserModel;

public interface IDataSourceUser {

    Boolean attemptLogin(String username, String password);

    UserModel getUserModel(String username);

    Boolean createUserModel(UserModel userModel);
}
