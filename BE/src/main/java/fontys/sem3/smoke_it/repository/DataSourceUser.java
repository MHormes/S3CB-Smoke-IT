package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.repository.jpaRepo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceUser implements IDataSourceUser {

    @Autowired
    IUserRepository repo;

    //Not needed since spring security
//    @Override
//    public Boolean attemptLogin(String username, String password) {
//        UserModel userModel =  repo.getFirstByUsername(username);
//        if (userModel != null){
//            return Objects.equals(userModel.getPassword(), password);
//        }
//        return false;
//    }

    @Override
    public UserModel getUserModel(String username) {
        return repo.getFirstByUsername(username);
    }

    @Override
    public Boolean createUserModel(UserModel userModel) {
        repo.save(userModel);
        return true;
    }
}
