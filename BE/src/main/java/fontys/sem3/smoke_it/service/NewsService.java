package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceNews;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService implements INewsService {

    @Autowired
    IDataSourceNews dataSource;

    @Override
    public void saveNewsMessage(NewsMessageModel message) {
        dataSource.saveNewsMessage(message);
    }

    @Override
    public List<NewsMessageModel> getNewestMessages() {
        List<NewsMessageModel> returnList = new ArrayList<>();
        List<NewsMessageModel> messageList = dataSource.getAllMessages();
        if(messageList.size() > 0){
            int amount = 5;
            if(messageList.size() <= 5){
                amount = messageList.size();
            }
            for(int i = 0; i < amount; i++){
                int x = 1 +i;
                returnList.add(messageList.get(messageList.size() -x));
            }
            return returnList;
        }
        return null;


    }
}
