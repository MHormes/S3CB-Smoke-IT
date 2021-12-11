package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.junit.jupiter.api.Assertions;
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
    void getUserModelExistsTest(){
        UserModel modelToExpect = new UserModel("admin", "admin", "testMail@mail.com", "ADMIN");
        userService.createUserModel(modelToExpect);
        UserModel userModel = userService.getUserModel("admin");

        assertTrue(modelToExpect.equals(userModel));
    }

    @Test
    void getUserModelNonExistingTest(){
        UserModel userModel = userService.getUserModel("non-existing");

        assertEquals(null, userModel);
    }

    @Test
    void createUserModelUserRoleSuccessfulTest(){
        UserModel userModel = userService.createUserModel(new UserModel("user", "user", "user@gmaicom", null));

        Assertions.assertEquals("USER", userModel.getRole());
    }

    @Test
    void createUserModelAdminRoleSuccessfulTest(){
        UserModel userModel = userService.createUserModel(new UserModel("admin", "admin", "admin@gmaicom", "ADMIN"));

        Assertions.assertEquals("ADMIN", userModel.getRole());
    }
}
