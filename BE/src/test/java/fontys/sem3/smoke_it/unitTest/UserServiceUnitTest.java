package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.fakeDB.FakeDataSourceUser;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.UserService;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceUnitTest {

    @Autowired
    private IUserService userService;


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
