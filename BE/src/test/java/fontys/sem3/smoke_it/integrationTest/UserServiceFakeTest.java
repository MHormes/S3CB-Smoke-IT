package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
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
        UserModel modelToExpect = new UserModel("admin", "admin", "testMail@mail.com", "ADMIN");
        userService.createUserModel(modelToExpect);
        UserModel userModel = userService.getUserModel("admin");

        assertTrue(modelToExpect.equals(userModel));
    }

    @Test
    void testGetUserModelNonExisting(){
        UserModel userModel = userService.getUserModel("non-existing");

        assertEquals(null, userModel);
    }
}
