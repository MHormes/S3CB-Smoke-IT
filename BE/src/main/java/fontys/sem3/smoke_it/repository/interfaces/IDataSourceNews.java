package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.NewsMessageModel;

public interface IDataSourceNews {

    void saveNewsMessage(NewsMessageModel message);
}
