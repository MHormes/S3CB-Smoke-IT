package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.dtos.UserDTO;
import fontys.sem3.smoke_it.model.UserModel;
import fontys.sem3.smoke_it.model.dtos.UserModelDTO;

public class UserModelConverter {

    public UserDTO convertModelToUserDTO(UserModel userModel){
        return new UserDTO(userModel.getUsername(), userModel.getRole());
    }

    public UserModelDTO convertModelToUserModelDTO(UserModel u){
        return new UserModelDTO(u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
    }

    public UserModel convertUserModelDTOToUserModel(UserModelDTO u){
        return new UserModel(u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
    }
}
