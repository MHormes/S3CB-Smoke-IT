package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.service.exceptions.BoxListNullException;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/boxes")
public class BoxController {

    private IBoxService boxService;

    public BoxController(IBoxService boxService) {
        this.boxService = boxService;
    }

    //not sure if i want to do this
    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes() throws BoxListNullException {
        List<BoxDTO> boxDTOList = new ArrayList<>();
        List<BoxModel> boxModelList = boxService.getAllBoxes();
        for (BoxModel box : boxModelList) {
            boxDTOList.add(new BoxDTO(box.getID(), box.getName(), box.getBasePrice(), box.getContent(), box.getDescription()));
        }
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoxDTO> getBoxWithID(@PathVariable(value = "id") String id) {
        BoxModel boxModel = boxService.getBoxWithID(id);
        if (boxModel != null) {
            return ResponseEntity.ok().body(new BoxDTO(boxModel.getID(), boxModel.getName(), boxModel.getBasePrice(), boxModel.getContent(), boxModel.getDescription()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BoxDTO>> getAllBoxesSorted(@RequestParam String sort) {
        List<BoxDTO> boxDTOListSorted = new ArrayList<>();
        List<BoxModel> boxModelListSorted = boxService.getAllBoxesSorted(sort);
        for(BoxModel box: boxModelListSorted){
            boxDTOListSorted.add(new BoxDTO(box.getID(), box.getName(), box.getBasePrice(), box.getContent(), box.getDescription()));
        }
        return ResponseEntity.ok().body(boxDTOListSorted);
    }

    @GetMapping("{id}/price")
    public ResponseEntity<Double> getBoxPrice(@PathVariable(value = "id") String id, @RequestParam int amount) {
        BoxModel boxModel = boxService.getBoxWithID(id);
        double price = boxService.calculateBoxPrice(boxModel, amount);
        return ResponseEntity.ok().body(price);
    }

    @PostMapping("/create")
    public ResponseEntity<BoxDTO> createBox(@RequestBody BoxDTO boxDTO) {
        BoxModel boxModel = new BoxModel(boxService.createID(), boxDTO.getName(), boxDTO.getBasePrice(), boxDTO.getContent(), boxDTO.getDescription());
        if (!boxService.createBox(boxModel)) {
            String entity = "Box with ID " + boxDTO.getID() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(url, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BoxDTO> updateBox(@RequestBody BoxDTO boxDTO) {
        BoxModel boxModel = new BoxModel(boxDTO.getID(), boxDTO.getName(), boxDTO.getBasePrice(), boxDTO.getContent(), boxDTO.getDescription());
        if (!boxService.updateBox(boxModel)) {
            String entity = "There is no box with supplied id: " + boxDTO.getID();
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBox(@PathVariable(value = "id") String id) {
        boxService.deleteBox(id);
        return ResponseEntity.ok().build();
    }
}
