package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.dtos.NewsMessageDTO;
import fontys.sem3.smoke_it.model.NewsMessageModel;

public class NewsMessageModelConverter {

    public NewsMessageModel convertDTOToModel(NewsMessageDTO message){
        return new NewsMessageModel(0L, message.getTitle(), message.getText(), message.getPostDate());
    }

    public NewsMessageDTO convertModelToDTO(NewsMessageModel message){
        return new NewsMessageDTO(message.getId(), message.getTitle(), message.getText(), message.getPostDate());
    }
}
