package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class BoxServiceFakeTest {

    @Autowired
    private IBoxService boxService;

    @Test
    void testGetAllBoxesSuccessful(){
        List<BoxModel> listToAssert = new ArrayList<>();

        BoxModel box1 = new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1");
        boxService.createBox(box1);
        listToAssert.add(box1);
        BoxModel box2 = new BoxModel("2", "test2", 1.00, "testContent2", "testDescription2", "testImagePath2");
        boxService.createBox(box2);
        listToAssert.add(box2);

        assertEquals(listToAssert, boxService.getAllBoxes());

    }

    @Test
    void testGetBoxWithIDSuccessful() {
        BoxModel modelToExpect = new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testImagePath1");
        boxService.createBox(modelToExpect);

        assertEquals(modelToExpect, boxService.getBoxWithID("1"));

    }

    @Test
    void testGetBoxWithIDInvalidID(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath"));

        assertNull(boxService.getBoxWithID("100"));
    }

    @Test
    void testCreateBoxCorrectInput(){
        BoxModel modelToAdd = new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath");
        BoxModel createdBox = boxService.createBox(modelToAdd);
        assertEquals(modelToAdd, createdBox);
    }

    @Test
    void testUpdateBoxCorrectInput(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath"));

        BoxModel updatedBox = new BoxModel("1", "update", 1.00, "update", "update", "update");

        BoxModel updateResult = boxService.updateBox(updatedBox);

        assertEquals(updatedBox, updateResult);
        assertEquals("1", updateResult.getId());
    }

    @Test
    void testUpdateBoxWithInvalidIdTest(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath"));

        BoxModel updatedBox = new BoxModel("2", "update", 1.00, "update", "update", "update");

        BoxModel updateResult = boxService.updateBox(updatedBox);

        //Box with id 2 does not exist. This means updateResult should be null
        Assertions.assertNull(updateResult);
    }

    @Test
    void testUpdateBoxWithEmptyImagePath(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath"));

        BoxModel updatedBox = new BoxModel("1", "update", 1.00, "update", "update", "");

        BoxModel updateResult = boxService.updateBox(updatedBox);

        //Box should be updated like testUpdateBoxCorrectInput. We check if imagePath is still the same "testPath".
        assertEquals(updatedBox, updateResult);
        assertEquals("testPath", updateResult.getImagePath());

    }

    @Test
    void testDeleteBoxWithValidID(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription", "testPath"));
        Boolean deleteResult = boxService.deleteBox("1");

        assertEquals(true, deleteResult);
    }

    @Test
    void testDeleteWithInvalidID() {
        Boolean deleteResult = boxService.deleteBox("testDeleteInvalidId");

        assertEquals(false, deleteResult);
    }

    @Test
    void testCalculatePriceTwoTimes(){
        boxService.createBox(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testPath1"));
        boxService.createBox(new BoxModel("2", "test2", 2.00, "testContent2", "testDescription2", "testPath2"));

        double price1 = boxService.calculateBoxPrice(boxService.getBoxWithID("1"), 2);
        double price2 = boxService.calculateBoxPrice(boxService.getBoxWithID("2"), 4);

        //every box extra is a 2% discount.
        assertEquals(0.96, price1);
        assertEquals(1.84, price2);
    }
}
