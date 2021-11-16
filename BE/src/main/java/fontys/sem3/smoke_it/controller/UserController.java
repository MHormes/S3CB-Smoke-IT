package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.model.modelconverters.UserModelConverter;
import fontys.sem3.smoke_it.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    private UserModelConverter userModelConverter;

    public UserController(IUserService userService){
        this.userService = userService;
        userModelConverter = new UserModelConverter();
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserModel userModel){
        if(userService.createUserModel(userModel)){
                return ResponseEntity.ok().body(userModel);
        }
        else{
            String entity = "Username: " + userModel.getUsername() + " is already taken";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);

        }
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
