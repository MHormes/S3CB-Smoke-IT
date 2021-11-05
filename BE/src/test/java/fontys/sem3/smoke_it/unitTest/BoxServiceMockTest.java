package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.interfaces.IBoxesRepository;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.service.BoxService;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


        when(datasource.updateBox(null)).thenReturn(false);
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
}
