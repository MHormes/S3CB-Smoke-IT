package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

    IDataSourceUser dataSource;
    @Autowired
    public UserService(@Qualifier("dataSourceUser") IDataSourceUser dataSource){ this.dataSource = dataSource;}

    @Override
    public Boolean attemptLogin(String username, String password) {
        return dataSource.attemptLogin(username, password);
    }

    @Override
    public UserModel getUserModel(String username) {
        return dataSource.getUserModel(username);
    }

    @Override
    public Boolean createUserModel(UserModel userModel) {
        return dataSource.createUserModel(userModel);
    }


}
