package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/boxes")
public class BoxController {

    private IBoxService boxService;

    public BoxController(IBoxService boxService){
        this.boxService = boxService;
    }

    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes()
    {
        List<BoxDTO> boxDTOList =  boxService.getAllBoxes();
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoxDTO> getBoxWithID(@PathVariable(value = "id") int id){
        BoxDTO boxDTO = boxService.getBoxWithID(id);
        if(boxDTO != null){
            return ResponseEntity.ok().body(boxDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BoxDTO>> getAllBoxesSorted(@RequestParam String sort){
            List<BoxDTO> boxDTOListSorted = boxService.getAllBoxesSorted(sort);
            return ResponseEntity.ok().body(boxDTOListSorted);
    }

    @GetMapping("{id}/price")
    public ResponseEntity<Double> getBoxPrice(@PathVariable(value = "id") int id, @RequestParam int amount){
        BoxDTO boxDTO = boxService.getBoxWithID(id);
        double price = boxService.calculateBoxPrice(boxDTO, amount);
        return ResponseEntity.ok().body(price);
    }

    @PostMapping()
    public ResponseEntity<BoxDTO> createBox(@RequestBody BoxDTO boxDTO){
        if(!boxService.createBox(boxDTO)){
            String entity = "Box with ID " + boxDTO.getID() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else{
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(url, HttpStatus.CREATED);
        }
    }

    @PutMapping
    public ResponseEntity<BoxDTO> updateBox(@RequestBody BoxDTO boxDTO){
        if(!boxService.updateBox(boxDTO)){
            String entity = "There is no box with supplied id: " + boxDTO.getID();
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else{
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBox(@PathVariable(value = "id") int id){
        boxService.deleteBox(id);
        return ResponseEntity.ok().build();
    }
}
