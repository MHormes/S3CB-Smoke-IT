package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.UserModel;

public interface IDataSourceUser {

    UserModel getUserModel(String username);

    Boolean createUserModel(UserModel userModel);
}
