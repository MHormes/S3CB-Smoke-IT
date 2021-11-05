package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.UserService;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceFakeTest {

    @Autowired
    private IUserService userService;

    @Test
    void testGetUserModelExists(){
        UserModel modelToExpect = new UserModel(1L, "admin", "admin", true);
        userService.createUserModel(modelToExpect);
        UserModel userModel = userService.getUserModel("admin");

        assertTrue(modelToExpect.equals(userModel));
    }

    @Test
    void testGetUserModelNonExisting(){
        UserModel userModel = userService.getUserModel("non-existing");

        assertEquals(null, userModel);
    }

    @Test
    void testLoginAttemptSuccessful(){
        UserModel modelToExpect = new UserModel(1L, "admin", "pass", true);
        userService.createUserModel(modelToExpect);
        Boolean loginResult = userService.attemptLogin("admin", "pass");

        assertEquals(true, loginResult);
    }

    @Test
    void testLoginAttemptUnSuccessful(){
        Boolean loginResult = userService.attemptLogin("fake", "fake");

        assertEquals(false, loginResult);
    }
}
