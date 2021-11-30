package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.NewsMessageDTO;
import fontys.sem3.smoke_it.model.NewsMessageModel;

public class NewsMessageModelConverter {

    public NewsMessageModel ConvertDTOToModel(NewsMessageDTO message){
        return new NewsMessageModel(0L, message.getTitle(), message.getText(), message.getPostDate());
    }
}
