package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.repository.JPARepo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository repo;

    @Override
    public UserModel getUserModel(String username) {
        return repo.getFirstByUsername(username);
    }

    @Override
    public UserModel createUserModel(UserModel userModel) {
        return repo.save(userModel);
    }
}
