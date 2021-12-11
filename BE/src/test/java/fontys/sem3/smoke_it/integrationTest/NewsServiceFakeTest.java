package fontys.sem3.smoke_it.integrationTest;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class NewsServiceFakeTest {

    @Autowired
    INewsService newsService;

    @Test
    void getNewestMessagesEmptyListTest(){
        List<NewsMessageModel> messageModelList = newsService.getNewestMessages();

        Assertions.assertNull(messageModelList);
    }

    @Test
    void getNewestMessagesLessThanFiveTest(){
        NewsMessageModel modelToAdd = new NewsMessageModel(1L, "title", "description", LocalDate.now());
        NewsMessageModel modelToAdd2 = new NewsMessageModel(2L, "title", "description", LocalDate.now());
        newsService.saveNewsMessage(modelToAdd);
        newsService.saveNewsMessage(modelToAdd2);

        List<NewsMessageModel> messageModelList = newsService.getNewestMessages();

        Assertions.assertEquals(2, messageModelList.size());
        Assertions.assertEquals(modelToAdd2, messageModelList.get(0));
        Assertions.assertEquals(modelToAdd, messageModelList.get(1));
    }

    @Test
    void createNewNewsMessageAndGetMessageByIdTest() throws InterruptedException {
        NewsMessageModel newsMessageModel = new NewsMessageModel(1L, "title", "description", LocalDate.now());
        newsService.saveNewsMessage(newsMessageModel);

        NewsMessageModel model = newsService.getMessageWithId(1L);

        Assertions.assertEquals(newsMessageModel, model);
    }

    @Test
    void deleteMessageExistingIdTest(){
        NewsMessageModel modelToAdd = new NewsMessageModel(4L, "title", "description", LocalDate.now());
        newsService.saveNewsMessage(modelToAdd);

        newsService.deleteMessageWithId(4L);

        Assertions.assertNull(newsService.getMessageWithId(4L));
    }


}
