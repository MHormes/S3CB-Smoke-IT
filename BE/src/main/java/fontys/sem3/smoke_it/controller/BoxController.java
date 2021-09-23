package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.Interfaces.IDataSource;
import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.repository.FakeDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.swing.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boxes")
public class BoxController {

    private static IDataSource fakeDataSource = new FakeDataSource();
    //private static IDataSource realDataSource = new realDataSource();


    // this has to be static because web services are stateless
    private static final IDataSource dataSource = fakeDataSource;

    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes()
    {
        List<BoxDTO> boxDTOList =  dataSource.getAllBoxes();
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<BoxDTO> getBoxWithID(@PathVariable(value = "id") int id){
        BoxDTO boxDTO = dataSource.getBoxWithID(id);
        if(boxDTO != null){
            return ResponseEntity.ok().body(boxDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BoxDTO>> getAllBoxesSorted(@RequestParam String sort){
            List<BoxDTO> boxDTOListSorted = dataSource.getAllBoxesSorted(sort);
            return ResponseEntity.ok().body(boxDTOListSorted);
    }

    @GetMapping("{id}/price")
    public ResponseEntity<Double> getBoxPrice(@PathVariable(value = "id") int id, @RequestParam int amount){
        BoxDTO boxDTO = dataSource.getBoxWithID(id);
        double price = dataSource.calculateBoxPrice(boxDTO, amount);
        return ResponseEntity.ok().body(price);
    }

    @PostMapping()
    public ResponseEntity<BoxDTO> createBox(@RequestBody BoxDTO boxDTO){
        if(!dataSource.createBox(boxDTO)){
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
        if(!dataSource.updateBox(boxDTO)){
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
        dataSource.deleteBox(id);
        return ResponseEntity.ok().build();
    }
}
