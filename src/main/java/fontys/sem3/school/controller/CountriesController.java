package fontys.sem3.school.controller;

import fontys.sem3.school.model.Country;
import fontys.sem3.school.repository.FakeDataStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    // this has to be static because web services are stateless
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

    @GetMapping("{id}")
    public ResponseEntity<Country> getCountryPath(@PathVariable(value = "id") String id){
        Country country = fakeDataStore.getCountry(id);

        if(country != null){
            return ResponseEntity.ok(country);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countryList = fakeDataStore.getCountries();
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCountry(@PathVariable(value = "id") String id){
        if(fakeDataStore.deleteCountry(id)){
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity("Country with code " + id + " was not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity createCountry(@RequestBody Country country){
        if(!fakeDataStore.createCountry(country)){
            String entity = "Country with code " + country.getCode() + " already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }
        else{
            String url = "countries/" + country.getCode();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity updateCountry(@RequestBody Country country){
        if (fakeDataStore.updateCountry(country)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid country code.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity updateCountry(@PathVariable(value = "id")String id, @RequestParam String name){
        Country country = fakeDataStore.getCountry(id);
        if(country == null){
            return new ResponseEntity("Please insert a valid country code", HttpStatus.NOT_FOUND);
        }
        country.setName(name);
        return ResponseEntity.ok().build();
    }
}
