package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.dtos.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.model.modelconverters.BoxModelConverter;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/boxes")
public class BoxController {

    private static final String imageDirectory = System.getProperty("user.dir") + "/images/";

    @Autowired
    private IBoxService boxService;
    private final BoxModelConverter boxModelConverter;

    public BoxController() {
        boxModelConverter = new BoxModelConverter();
    }


    @GetMapping()
    public ResponseEntity<List<BoxDTO>> getAllBoxes() {
        List<BoxDTO> boxDTOList = boxModelConverter.convertModelListToDTO(boxService.getAllBoxes());
        return ResponseEntity.ok().body(boxDTOList);
    }

    @GetMapping("/{id}")
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

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getBoxPrice(@PathVariable(value = "id") String id, @RequestParam int amount) {
        BoxModel boxModel = boxService.getBoxWithID(id);
        if (boxModel != null) {
            double price = boxService.calculateBoxPrice(boxModel, amount);
            return ResponseEntity.ok().body(price);
        }
        return ResponseEntity.ok().body(2.00);
    }

    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BoxDTO> createBox(@ModelAttribute BoxDTO boxDTO) {
        //assign unique id
        boxDTO.setId(boxService.createID());
        //create file path
        createImageFilePath(boxDTO);
        //convert completed dto to model for db add
        BoxModel boxModel = boxService.createBox(boxModelConverter.convertDTOToModel(boxDTO));
        BoxDTO addedBox = boxModelConverter.convertModelToDTO(boxModel);
        if (addedBox == null) {
            String entity = "Box with ID " + boxDTO.getId() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.ok().body(addedBox);
        }
    }

    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BoxDTO> updateBox(@ModelAttribute BoxDTO boxDTO) {
        if(boxDTO.getImageFile() != null){
            //create new file path
            createImageFilePath(boxDTO);
        }else{
            boxDTO.setImagePath(Path.of(""));
        }

        BoxModel boxModel = boxModelConverter.convertDTOToModel(boxDTO);
        if (boxService.updateBox(boxModel) == null) {
            String entity = "There is no box with supplied id: " + boxDTO.getId() + " (box is called: " + boxDTO.getName() + ")";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBox(@PathVariable(value = "id") String id) {
        Boolean result = boxService.deleteBox(id);
        return ResponseEntity.ok().body(result);
    }

    private void makeDirectoryIfNotExist() {
        File directory = new File(BoxController.imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private void createImageFilePath(BoxDTO boxDTO) {
        //Create directory for images
        makeDirectoryIfNotExist();
        //create file path
        Path imagePath = Paths.get(imageDirectory,
                boxDTO.getName().concat(".").concat(Objects.requireNonNull(FilenameUtils.getExtension(boxDTO.getImageFile().getOriginalFilename()))));
        //assign file to filepath
        try {
            Files.write(imagePath, boxDTO.getImageFile().getBytes());
            boxDTO.setImagePath(imagePath);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
