package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.repository.FakeDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boxes")
public class BoxController {

    // this has to be static because web services are stateless
    private static final FakeDataSource fakeDataSource = new FakeDataSource();

    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes()
    {
        List<BoxDTO> boxDTOList =  fakeDataSource.getAllBoxes();
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoxDTO> getBoxWithID(@PathVariable(value = "id") int id){
        BoxDTO boxDTO = fakeDataSource.getBoxWithID(id);
        if(boxDTO != null){
            return ResponseEntity.ok().body(boxDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BoxDTO>> getAllBoxesSorted(@RequestParam String sort){
            List<BoxDTO> boxDTOListSorted = fakeDataSource.getAllBoxesSorted(sort);
            return ResponseEntity.ok().body(boxDTOListSorted);
    }

    @PostMapping()
    public ResponseEntity<BoxDTO> createBox(@RequestBody BoxDTO boxDTO){
        if(!fakeDataSource.createBox(boxDTO)){
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
        if(!fakeDataSource.updateBox(boxDTO)){
            String entity = "There is no box with supplied id: " + boxDTO.getID();
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else{
            String url = "boxes/" + boxDTO.getID();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBox(@PathVariable(value = "id") int id){
        fakeDataSource.deleteBox(id);
        return ResponseEntity.ok().build();
    }
}
