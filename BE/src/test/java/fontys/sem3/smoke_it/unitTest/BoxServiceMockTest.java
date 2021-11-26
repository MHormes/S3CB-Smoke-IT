package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.service.BoxService;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
public class BoxServiceMockTest {

    @Mock
    IDataSourceBoxes datasource;

    IBoxService boxService;

    @BeforeEach
    void setUp(){
        boxService = new BoxService(datasource);
        BoxModel boxModel = new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1");
        List<BoxModel> mockList = new ArrayList<>();
        mockList.add(boxModel);
        mockList.add(new BoxModel("2", "test2", 2.00, "testContent2", "testDescription2", "testImagePath2"));
        when(datasource.getAllBoxes()).thenReturn(mockList);
        when(datasource.updateBox(boxModel)).thenReturn(true);
    }

    @Test
    void testUpdateBoxSuccessful(){
        BoxModel boxModel = new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1");
        boolean result = boxService.updateBox(boxModel);
        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateBoxNullValue(){
        boolean result = boxService.updateBox(null);
        Assertions.assertFalse(result);
    }

    @Test
    void testGetAllBoxesSortedLowHigh(){
        List<BoxModel> sortedList = boxService.getAllBoxesSorted("l-h");

        Assertions.assertEquals(1 ,sortedList.get(0).getBasePrice());
        Assertions.assertEquals(2 ,sortedList.get(1).getBasePrice());

    }

    @Test
    void testGetAllBoxesSortedHighToLow(){
        List<BoxModel> sortedList = boxService.getAllBoxesSorted("h-l");

        Assertions.assertEquals(2 ,sortedList.get(0).getBasePrice());
        Assertions.assertEquals(1 ,sortedList.get(1).getBasePrice());
    }

    @Test
    void testCalculateBoxPrice(){
        double price = boxService.calculateBoxPrice(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1"), 2);
        double expectedPrice = 0.96;

        Assertions.assertEquals(expectedPrice, price);
    }

}
