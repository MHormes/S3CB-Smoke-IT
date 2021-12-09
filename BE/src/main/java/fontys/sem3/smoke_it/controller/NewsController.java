package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.NewsMessageDTO;
import fontys.sem3.smoke_it.model.NewsMessageModel;
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
@CrossOrigin("http://localhost:3000")
@RequestMapping("/newsFeed")
public class NewsController {

    @Autowired
    INewsService newsService;

    NewsMessageModelConverter modelConverter = new NewsMessageModelConverter();

    @GetMapping("")
    public ResponseEntity<List<NewsMessageDTO>> getNewestMessages() {
        return ResponseEntity.ok().body(getNewestMessageDTO());
    }

    @MessageMapping("/postNews")
    @SendTo("/news/feed")
    public List<NewsMessageDTO> castNewMessage(@RequestBody NewsMessageDTO newsDTO) {
        newsDTO.setPostDate(LocalDate.now());
        NewsMessageModel message = modelConverter.ConvertDTOToModel(newsDTO);
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
        List<NewsMessageDTO> dtoList = new ArrayList<>();
        for (NewsMessageModel m : modelList) {
            dtoList.add(modelConverter.ConvertModelToDTO(m));
        }
        return dtoList;
    }
}
