package fontys.sem3.smoke_it.controller;

import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.model.dtos.UserModelDTO;
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

    UserModelConverter modelConverter;

    public UserController(IUserService userService) {
        this.userService = userService;
        this.modelConverter = new UserModelConverter();
    }

    @PostMapping("/register")
    public ResponseEntity<UserModelDTO> createUser(@RequestBody UserModelDTO userModelDTO) {
        UserModel model = userService.createUserModel(modelConverter.convertUserModelDTOToUserModel(userModelDTO));
        if (model != null) {
            return ResponseEntity.ok().body(modelConverter.convertModelToUserModelDTO(model));
        } else {
            String entity = "Username: " + userModelDTO.getUsername() + " is already taken";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);

        }
    }

}
