package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.DataSourceBoxes;
import fontys.sem3.smoke_it.repository.FakeDataSourceBoxes;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.service.BoxService;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BoxServiceUnitTest {

    //@Autowired
    private IBoxService boxService;

    //Below is needed for fake testing.
    //Still need to figure out how to test the H2 db
    private IDataSourceBoxes dataSource;
    @BeforeEach
    public void arrangeBoxTest(){
        dataSource = new FakeDataSourceBoxes();
        boxService = new BoxService(dataSource);
    }

    //Below method uses the overloaded fakeDataSource const to have an empty list inside the datasource
    @Test
    public void testGetAllBoxesSuccessful(){
        IDataSourceBoxes dataSource = new FakeDataSourceBoxes("test");
        IBoxService boxService = new BoxService(dataSource);
        List<BoxModel> listToAssert = new ArrayList<>();

        BoxModel box1 = new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1");
        boxService.createBox(box1);
        listToAssert.add(box1);
        BoxModel box2 = new BoxModel("2", "test2", 1.00, "testContent2", "testDescription2");
        boxService.createBox(box2);
        listToAssert.add(box2);

        assertEquals(listToAssert, boxService.getAllBoxes());

    }

    @Test
    public void testGetBoxWithIDSuccessful() {
        BoxModel modelToExpect = new BoxModel("1", "test", 1.00, "testContent", "testDescription");
        boxService.createBox(modelToExpect);

        BoxModel boxToAssert = boxService.getBoxWithID("1");;
        assertEquals(true, modelToExpect.equals(boxToAssert));

    }

    @Test
    public void testGetBoxWithIDInvalidID(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));

        assertNull(boxService.getBoxWithID("2"));
    }

    @Test
    public void testCreateBoxCorrectInput(){
        Boolean createResult = boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));

        assertEquals(true, createResult);
    }

    //Would this be needed with the UUID?
    @Test
    public void testCreateBoxWithExistingID(){
        boxService.createBox(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1"));
        Boolean createResult = boxService.createBox(new BoxModel("1", "test2", 1.00, "testContent2", "testDescription2"));

        assertEquals(false, createResult);
    }

    @Test
    public void testUpdateBoxCorrectInput(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean updateResult = boxService.updateBox(boxService.getBoxWithID("1"));

        assertEquals(true, updateResult);
    }

    @Test
    public void testUpdateBoxWithIncorrectInput(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean updateResult = boxService.updateBox(boxService.getBoxWithID("2"));

        assertEquals(false, updateResult);
    }

    @Test
    public void testDeleteBoxWithValidID(){
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean deleteResult = boxService.deleteBox("1");

        assertEquals(true, deleteResult);
    }

    @Test
    public void testDeleteWithInvalidID() {
        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean deleteResult = boxService.deleteBox("2");

        assertEquals(false, deleteResult);
    }

    @Test
    public void testCalculatePriceTwoTimes(){
        boxService.createBox(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription2"));
        boxService.createBox(new BoxModel("2", "test2", 2.00, "testContent2", "testDescription2"));

        double price1 = boxService.calculateBoxPrice(boxService.getBoxWithID("1"), 2);
        double price2 = boxService.calculateBoxPrice(boxService.getBoxWithID("2"), 4);

        //every box extra is a 2% discount.
        assertEquals(0.96, price1);
        assertEquals(1.84, price2);
    }
}
