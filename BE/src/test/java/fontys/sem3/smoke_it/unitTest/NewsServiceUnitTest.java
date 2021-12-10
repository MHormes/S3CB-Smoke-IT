package fontys.sem3.smoke_it.unitTest;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceNews;
import fontys.sem3.smoke_it.service.NewsService;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class NewsServiceUnitTest {

    @Mock
    IDataSourceNews dataSourceNews;

    INewsService newsService;

    @BeforeEach
    void setUp() {
        this.newsService = new NewsService(dataSourceNews);
        NewsMessageModel model1 = new NewsMessageModel(1L, "title1", "text1", LocalDate.now());
        NewsMessageModel model2 = new NewsMessageModel(2L, "title2", "text2", LocalDate.now());
        NewsMessageModel model3 = new NewsMessageModel(3L, "title3", "text3", LocalDate.now());
        NewsMessageModel model4 = new NewsMessageModel(4L, "title4", "text4", LocalDate.now());
        NewsMessageModel model5 = new NewsMessageModel(5L, "title5", "text5", LocalDate.now());
        NewsMessageModel model6 = new NewsMessageModel(6L, "title6", "text6", LocalDate.now());

        List<NewsMessageModel> mockList = new ArrayList<>();
        mockList.add(model1);
        mockList.add(model2);
        mockList.add(model3);
        mockList.add(model4);
        mockList.add(model5);
        mockList.add(model6);

        when(dataSourceNews.getAllMessages()).thenReturn(mockList);
    }

    @Test
    void getFiveNewestMessagesTest() {
        List<NewsMessageModel> messageModelList = newsService.getNewestMessages();

        //Assert if only 5 items are returned
        Assertions.assertEquals(5, messageModelList.size());
        //Assert if are the 5 newest by checking if id 1 is present
        for (NewsMessageModel m : messageModelList) {
            Assertions.assertNotEquals(1, m.getId());
        }

    }
}
