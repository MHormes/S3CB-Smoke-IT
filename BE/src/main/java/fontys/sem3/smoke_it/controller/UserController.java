package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.UserModel;
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

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        UserModel model = userService.createUserModel(userModel);
        if (model != null) {
            return ResponseEntity.ok().body(userModel);
        } else {
            String entity = "Username: " + userModel.getUsername() + " is already taken";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);

        }
    }

}
