package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.NewsMessageDTO;
import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.model.modelconverters.NewsMessageModelConverter;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Controller
public class NewsController {

    @Autowired
    INewsService newsService;

    NewsMessageModelConverter modelConverter = new NewsMessageModelConverter();

    @MessageMapping("/postNews")
    @SendTo("/news/feed")
    public NewsMessageDTO castNewMessage(@RequestBody NewsMessageDTO news) {
        news.setPostDate(LocalDate.now());
        NewsMessageModel message = modelConverter.ConvertDTOToModel(news);
        newsService.saveNewsMessage(message);
        return news;
    }

}
