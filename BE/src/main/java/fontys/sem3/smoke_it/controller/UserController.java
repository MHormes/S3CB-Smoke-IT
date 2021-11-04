package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.UserDTO;
import fontys.sem3.smoke_it.model.modelconverters.UserModelConverter;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    private UserModelConverter userModelConverter;

    public UserController(){
        userModelConverter = new UserModelConverter();
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginApplication(@RequestParam String username, String password) {
        Boolean loginResult = userService.attemptLogin(username, password);
        if (Boolean.TRUE.equals(loginResult)) {
            UserDTO userDTOToReturn = userModelConverter.convertModelToDTO(userService.getUserModel(username));
            return ResponseEntity.ok().body(userDTOToReturn);
        }
        return ResponseEntity.notFound().build();
    }
}
