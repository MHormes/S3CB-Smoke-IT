package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.repository.interfaces.IDataSource;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

    IDataSource dataSource;

    public UserService(IDataSource dataSource){ this.dataSource = dataSource;}

}
