package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.UserService;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceUnitTest {

    @Mock
    IDataSourceUser dataSourceUser;

    IUserService userService;
    UserModel userModel;
    UserModel adminModel;

    @BeforeEach
    void setUp(){
        this.userService = new UserService(dataSourceUser);
        this.userModel = new UserModel("username", "password", null);
        this.adminModel = new UserModel("admin", "password", "ADMIN");

        when(dataSourceUser.getUserModel("admin")).then(null);
        when(dataSourceUser.getUserModel("username")).thenReturn(userModel);
        when(dataSourceUser.createUserModel(userModel)).thenReturn(userModel);
    }

//    @Test
//    void createAccountExistingUsername(){
//        UserModel returnModel = userService.createUserModel(userModel);
//
//        Assertions.assertNull(returnModel);
//    }
}
