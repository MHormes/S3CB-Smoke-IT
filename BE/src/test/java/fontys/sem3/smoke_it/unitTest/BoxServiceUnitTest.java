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
class BoxServiceUnitTest {

    @Mock
    IDataSourceBoxes datasource;

    IBoxService boxService;

    @BeforeEach
    void setUp() {
        boxService = new BoxService(datasource);
        BoxModel boxModel1 = new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1");
        BoxModel boxModel2 = new BoxModel("2", "test2", 2.00, "testContent2", "testDescription2", "testImagePath2");
        List<BoxModel> mockList = new ArrayList<>();
        mockList.add(boxModel1);
        mockList.add(boxModel2);
        when(datasource.getAllBoxes()).thenReturn(mockList);
    }

    @Test
    void updateBoxNullValueTest() {
        BoxModel updatedBox = boxService.updateBox(null);
        Assertions.assertNull(updatedBox);
    }

    @Test
    void getAllBoxesSortedLowHighTest() {
        List<BoxModel> sortedList = boxService.getAllBoxesSorted("l-h");

        Assertions.assertEquals(1, sortedList.get(0).getBasePrice());
        Assertions.assertEquals(2, sortedList.get(1).getBasePrice());

    }

    @Test
    void getAllBoxesSortedHighToLowTest() {
        List<BoxModel> sortedList = boxService.getAllBoxesSorted("h-l");

        Assertions.assertEquals(2, sortedList.get(0).getBasePrice());
        Assertions.assertEquals(1, sortedList.get(1).getBasePrice());
    }

    //Calling sort method without any sort string. This results in the basic list being returned
    @Test
    void etAllBoxesSortedWithoutSortStringTest(){
        List<BoxModel> sortedList = boxService.getAllBoxesSorted("");

        Assertions.assertEquals("test1", sortedList.get(0).getName());
        Assertions.assertEquals("test2", sortedList.get(1).getName());
    }

    @Test
    void calculateBoxPriceAmountTwoTest() {
        double price = boxService.calculateBoxPrice(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1"), 2);
        double expectedPrice = 0.96;

        Assertions.assertEquals(expectedPrice, price);
    }

    @Test
    void calculateBoxPriceAmountOneTest() {
        //Amount 1 should return the basePrice
        double price = boxService.calculateBoxPrice(new BoxModel("1", "test1", 1.00, "testContent1", "testDescription1", "testImagePath1"), 1);
        double expectedPrice = 1;

        Assertions.assertEquals(expectedPrice, price);
    }

}
