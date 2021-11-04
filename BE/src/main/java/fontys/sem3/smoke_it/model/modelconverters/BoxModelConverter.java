package fontys.sem3.smoke_it.model.modelconverters;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BoxModelConverter {

    public List<BoxDTO> convertModelListToDTO(List<BoxModel> modelList){
        List<BoxDTO> boxDTOList = new ArrayList<>();
        for (BoxModel box : modelList) {
            boxDTOList.add(convertModelToDTO(box));
        }
        return boxDTOList;
    }

    public BoxDTO convertModelToDTO(BoxModel boxModel){
        BoxDTO boxDTO =  new BoxDTO(boxModel.getId(), boxModel.getName(), boxModel.getBasePrice(), boxModel.getContent(), boxModel.getDescription());
        boxDTO.setImagePath(Path.of(boxModel.getImagePath()));
        return boxDTO;
    }

    public BoxModel convertDTOToModel(BoxDTO boxDTO){
        return new BoxModel(boxDTO.getId(), boxDTO.getName(), boxDTO.getBasePrice(), boxDTO.getContent(), boxDTO.getDescription(), boxDTO.getImagePath().toString());
    }
}
