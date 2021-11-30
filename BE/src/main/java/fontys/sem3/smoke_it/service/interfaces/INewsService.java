package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import org.springframework.stereotype.Service;

@Service
public interface INewsService {

    void saveNewsMessage(NewsMessageModel message);
}
