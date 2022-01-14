package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import fontys.sem3.smoke_it.model.dtos.NewsMessageDTO;
import fontys.sem3.smoke_it.model.modelconverters.NewsMessageModelConverter;
import fontys.sem3.smoke_it.service.interfaces.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("http://127.0.0.1:3000")
@RequestMapping("/newsFeed")
public class NewsController {


    INewsService newsService;
    NewsMessageModelConverter modelConverter;

    @Autowired
    public NewsController(INewsService newsService) {
        this.modelConverter = new NewsMessageModelConverter();
        this.newsService = newsService;
    }

    @GetMapping("")
    public ResponseEntity<List<NewsMessageDTO>> getNewestMessages() {
        return ResponseEntity.ok().body(getNewestMessageDTO());
    }

    @MessageMapping("/postNews")
    @SendTo("/news/feed")
    public List<NewsMessageDTO> castNewMessage(@RequestBody NewsMessageDTO newsDTO) {
        newsDTO.setPostDate(LocalDate.now());
        NewsMessageModel message = modelConverter.convertDTOToModel(newsDTO);
        newsService.saveNewsMessage(message);

        return this.getNewestMessageDTO();
    }


    @DeleteMapping("/delete/{id}")
    @SendTo("/news/feed")
    public ResponseEntity<List<NewsMessageDTO>> deleteMessageWithId(@PathVariable(value = "id") String id) {
        newsService.deleteMessageWithId(Long.valueOf(id));
        return ResponseEntity.ok().body(getNewestMessageDTO());
    }


    private List<NewsMessageDTO> getNewestMessageDTO() {
        List<NewsMessageModel> modelList = newsService.getNewestMessages();
        if(modelList != null){
            List<NewsMessageDTO> dtoList = new ArrayList<>();
            for (NewsMessageModel m : modelList) {
                dtoList.add(modelConverter.convertModelToDTO(m));
            }
            return dtoList;
        }
        return null;
    }
}
