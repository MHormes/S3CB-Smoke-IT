package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceNews;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService implements INewsService {

    @Autowired
    IDataSourceNews dataSource;

    @Override
    public void saveNewsMessage(NewsMessageModel message) {
        dataSource.saveNewsMessage(message);
    }
}
