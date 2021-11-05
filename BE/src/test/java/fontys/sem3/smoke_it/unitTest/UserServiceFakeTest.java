package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.repository.fakeDB.FakeDataSourceUser;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceUser;
import fontys.sem3.smoke_it.service.BoxService;
import fontys.sem3.smoke_it.service.UserService;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceFakeTest {

    //@Autowired
    private IUserService userService;

    //Below is needed for fake testing.
    //Still need to figure out how to test the H2 db
    private IDataSourceUser dataSource;
    @BeforeEach
    void arrangeUserTest(){
        dataSource = new FakeDataSourceUser();
        userService = new UserService(dataSource);
    }


    @Test
    void testGetUserModelExists(){
        UserModel modelToExpect = new UserModel("admin", "admin", true);
        UserModel userModel = userService.getUserModel("admin");

        assertEquals(modelToExpect.equals(modelToExpect.hashCode()), userModel.equals(userModel.hashCode()));
    }

    @Test
    void testGetUserModelNonExisting(){
        UserModel userModel = userService.getUserModel("non-existing");

        assertEquals(null, userModel);
    }

    @Test
    void testLoginAttemptSuccessful(){
        Boolean loginResult = userService.attemptLogin("admin", "admin");

        assertEquals(true, loginResult);
    }

    @Test
    void testLoginAttemptUnSuccessful(){
        Boolean loginResult = userService.attemptLogin("fake", "fake");

        assertEquals(false, loginResult);
    }
}
