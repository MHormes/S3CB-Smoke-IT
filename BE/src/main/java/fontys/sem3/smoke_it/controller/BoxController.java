package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.model.modelConverters.BoxModelConverter;
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
    private BoxModelConverter boxModelConverter;

    public BoxController(IBoxService boxService) {
        this.boxService = boxService;
        boxModelConverter = new BoxModelConverter();
    }


    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes() {
        List<BoxDTO> boxDTOList = boxModelConverter.convertModelListToDTO(boxService.getAllBoxes());
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoxDTO> getBoxWithID(@PathVariable(value = "id") String id) {
        BoxModel boxModel = boxService.getBoxWithID(id);
        if (boxModel != null) {
            BoxDTO boxDTO = boxModelConverter.convertModelToDTO(boxModel);
            return ResponseEntity.ok().body(boxDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BoxDTO>> getAllBoxesSorted(@RequestParam String sort) {
        List<BoxDTO> boxDTOListSorted = boxModelConverter.convertModelListToDTO(boxService.getAllBoxesSorted(sort));
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
        boxDTO.setID(boxService.createID());
        BoxModel boxModel = boxModelConverter.convertDTOToModel(boxDTO);
        if (!boxService.createBox(boxModel)) {
            String entity = "Box with ID " + boxDTO.getID() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BoxDTO> updateBox(@RequestBody BoxDTO boxDTO) {
        BoxModel boxModel = boxModelConverter.convertDTOToModel(boxDTO);
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
