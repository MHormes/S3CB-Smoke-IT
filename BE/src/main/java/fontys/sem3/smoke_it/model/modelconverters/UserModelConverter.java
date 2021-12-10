package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.dtos.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;

public class UserModelConverter {

    public UserDTO convertModelToDTO(UserModel userModel){
        return new UserDTO(userModel.getUsername(), userModel.getRole());
    }

}
