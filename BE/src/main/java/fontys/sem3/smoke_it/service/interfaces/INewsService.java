package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface INewsService {

    void saveNewsMessage(NewsMessageModel message);

    List<NewsMessageModel> getNewestMessages();

    void deleteMessageWithId(Long id);

    NewsMessageModel getMessageWithId(Long id);
}
