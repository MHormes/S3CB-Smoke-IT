package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.NewsMessageModel;

import java.util.List;

public interface IDataSourceNews {

    void saveNewsMessage(NewsMessageModel message);

    List<NewsMessageModel> getAllMessages();

    void deleteMessageWithId(Long id);

    NewsMessageModel getMessageWithId(Long id);
}
