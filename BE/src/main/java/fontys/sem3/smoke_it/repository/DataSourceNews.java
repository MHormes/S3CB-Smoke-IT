package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceNews;
import fontys.sem3.smoke_it.repository.JPARepo.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataSourceNews implements IDataSourceNews {

    @Autowired
    INewsRepository repo;

    @Override
    public void saveNewsMessage(NewsMessageModel message) {
        repo.save(message);
    }

    @Override
    public List<NewsMessageModel> getAllMessages() {
        return repo.findAll();
    }

    @Override
    public void deleteMessageWithId(Long id) {
        repo.deleteById(id);
    }

    @Override
    public NewsMessageModel getMessageWithId(Long id) {
        return repo.getNewsMessageModelById(id);
    }
}
