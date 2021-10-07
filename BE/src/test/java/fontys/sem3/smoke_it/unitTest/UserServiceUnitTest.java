package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.FakeDataSourceUser;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.UserService;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceUnitTest {

    private IDataSourceUser dataSource;
    private IUserService userService;

    @BeforeEach
    public void arrangeUserTest(){
        this.dataSource = new FakeDataSourceUser();
        this.userService = new UserService(dataSource);
    }

    @Test
    public void testGetUserModelExists(){
        UserModel modelToExpect = new UserModel("admin", "admin", true);
        UserModel userModel = userService.getUserModel("admin");

        assertEquals(modelToExpect.equals(modelToExpect.hashCode()), userModel.equals(userModel.hashCode()));
    }

    @Test
    public void testGetUserModelNonExisting(){
        UserModel userModel = userService.getUserModel("non-existing");

        assertEquals(null, userModel);
    }

    @Test
    public void testLoginAttemptSuccessful(){
        Boolean loginResult = userService.attemptLogin("admin", "admin");

        assertEquals(true, loginResult);
    }

    @Test
    public void testLoginAttemptUnSuccessful(){
        Boolean loginResult = userService.attemptLogin("fake", "fake");

        assertEquals(false, loginResult);
    }
}
