package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.UserModel;

public interface IDataSourceUser {

    public Boolean attemptLogin(String username, String password);

    public UserModel getUserModel(String username);
}
