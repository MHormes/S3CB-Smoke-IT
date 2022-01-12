package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {


    IDataSourceUser dataSource;

    BCryptPasswordEncoder passEncoder;

    @Autowired
    public UserService(IDataSourceUser dataSource) {
        this.dataSource = dataSource;
        this.passEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserModel getUserModel(String username) {
        return dataSource.getUserModel(username);
    }

    @Override
    public UserModel createUserModel(UserModel userModel) {
        UserModel byUsername = dataSource.getUserModel(userModel.getUsername());
        if (byUsername != null) {
            return null;
        }
        if(userModel.getRole() == null){
            userModel.setRole("USER");
        }
        userModel.setPassword(passEncoder.encode(userModel.getPassword()));
        return dataSource.createUserModel(userModel);
    }
}
