package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository repo;

    @Override
    public Boolean attemptLogin(String username, String password) {
        UserModel userModel =  repo.getFirstByUsername(username);
        if (userModel != null){
            return Objects.equals(userModel.getPassword(), password);
        }
        return false;
    }

    @Override
    public UserModel getUserModel(String username) {
        UserModel usermodel = repo.getFirstByUsername(username);
        return usermodel;
    }

    @Override
    public Boolean createUserModel(UserModel userModel) {
        repo.save(userModel);
        return true;
    }
}
