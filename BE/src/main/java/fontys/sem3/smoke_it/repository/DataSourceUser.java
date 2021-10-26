package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.repository.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository repo;

    @Override
    public Boolean attemptLogin(String username, String password) {
        UserModel userModel =  repo.getFirstByUsername(username);
        return Objects.equals(userModel.getPassword(), password);
    }

    @Override
    public UserModel getUserModel(String username) {
        return repo.getFirstByUsername(username);
    }
}
