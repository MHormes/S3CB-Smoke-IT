package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceNews;
import fontys.sem3.smoke_it.repository.interfaces.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceNews implements IDataSourceNews {

    @Autowired
    INewsRepository repo;

    @Override
    public void saveNewsMessage(NewsMessageModel message) {
        repo.save(message);
    }
}
