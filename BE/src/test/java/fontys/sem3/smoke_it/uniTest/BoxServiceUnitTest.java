package fontys.sem3.smoke_it.uniTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.FakeDataSource;
import fontys.sem3.smoke_it.repository.interfaces.IDataSource;
import fontys.sem3.smoke_it.service.BoxService;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//how do i use this?
public class BoxServiceUnitTest {
/*
    @Test
    public void testGetBoxWithID() {
        IDataSource dataSource = new FakeDataSource();
        IBoxService boxService = new BoxService(dataSource);

        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));

        assertEquals("test", boxService.getBoxWithID("1"));
    }

    @Test
    public void testCreateBoxCorrectInput(){
        IDataSource dataSource = new FakeDataSource();
        IBoxService boxService = new BoxService(dataSource);

        Boolean createResult = boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));

        assertEquals(createResult, true);
    }

    //Would this be needed with the UUID?
    @Test
    public void testCreateBoxWithExistingID(){
        IDataSource dataSource = new FakeDataSource();
        IBoxService boxService = new BoxService(dataSource);

        boxService.createBox(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1"));
        Boolean createResult = boxService.createBox(new BoxModel("1", "test2", 1.00, "testContent2", "testDescription2"));

        assertEquals(createResult, false);
    }

    @Test
    public void testUpdateBoxCorrectInput(){
        IDataSource dataSource = new FakeDataSource();
        IBoxService boxService = new BoxService(dataSource);

        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean updateResult = boxService.updateBox(boxService.getBoxWithID("1"));

        assertEquals(updateResult, true);
    }

    @Test
    public void testUpdateBoxWithIncorrectInput(){
        IDataSource dataSource = new FakeDataSource();
        IBoxService boxService = new BoxService(dataSource);

        boxService.createBox(new BoxModel("1", "test", 1.00, "testContent", "testDescription"));
        Boolean updateResult = boxService.updateBox(boxService.getBoxWithID("2"));

        assertEquals(updateResult, false);
    }*/
}
