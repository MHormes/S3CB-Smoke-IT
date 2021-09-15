package fontys.sem3.school.controller;

import fontys.sem3.school.model.Country;
import fontys.sem3.school.model.Student;
import fontys.sem3.school.repository.FakeDataStore;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentsController {


    // this has to be static because web services are stateless
    private static final FakeDataStore fakeDataStore = new FakeDataStore();

    // map to /students/hello
    @GetMapping("/hello")
    // return only the body, and let Spring boot handle http statuscode
    // and header
    @ResponseBody
    public String SayHello()
    {
        String msg = "Hello, your resources work!";
        return msg;
    }


    @GetMapping("{id}") //GET at http://localhost:XXXX/students/3
    public ResponseEntity<Student> getStudentPath(@PathVariable(value = "id") int id) {
        Student student = fakeDataStore.getStudent(id);

        if(student != null) {
            return ResponseEntity.ok().body(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get at /students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(value = "country") Optional<String> country) {
        List<Student> students = null;
        // if a country is added, us that method from the fakedatastore
        if(country.isPresent()) {
            Country c = fakeDataStore.getCountry(country.get());
            students = fakeDataStore.getStudents(c);
        }
        else
        {
            students = fakeDataStore.getStudents();
        }

        if(students != null) {
            return ResponseEntity.ok().body(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/students/3
    public ResponseEntity deletePost(@PathVariable int id) {
        fakeDataStore.deleteStudent(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();

    }

    @PostMapping()
    //POST at http://localhost:XXXX/students/
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (!fakeDataStore.add(student)){
            String entity =  "Student with student number " + student.getStudentNumber() + " already exists.";
            return new ResponseEntity(entity,HttpStatus.CONFLICT);
        } else {
            String url = "student" + "/" + student.getStudentNumber(); // url of the created student
            URI uri = URI.create(url);
            return new ResponseEntity(uri,HttpStatus.CREATED);
        }

    }

    @PutMapping()
    //PUT at http://localhost:XXXX/students/
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        // Idempotent method. Always update (even if the resource has already been updated before).
        if (fakeDataStore.update(student)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid student number.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/students/{id}
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id,  @RequestParam("name") String name, @RequestParam("country") String countryCode) {
        Student student = fakeDataStore.getStudent(id);
        if (student == null){
            return new ResponseEntity("Please provide a valid student number.",HttpStatus.NOT_FOUND);
        }

        Country country = fakeDataStore.getCountry(countryCode);
        if (country == null){
            return new ResponseEntity("Please provide a valid country code.",HttpStatus.BAD_REQUEST);
        }

        student.setName(name);
        student.setCountry(country);
        return ResponseEntity.noContent().build();
    }




}
