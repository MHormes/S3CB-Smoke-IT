package fontys.sem3.smoke_it.model.modelConverters;

import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;

import java.util.ArrayList;
import java.util.List;

public class BoxModelConverter {

    public List<BoxDTO> convertModelListToDTO(List<BoxModel> modelList){
        List<BoxDTO> boxDTOList = new ArrayList<>();
        for (BoxModel box : modelList) {
            boxDTOList.add(new BoxDTO(box.getID(), box.getName(), box.getBasePrice(), box.getContent(), box.getDescription()));
        }
        return boxDTOList;
    }

    public BoxDTO convertModelToDTO(BoxModel boxModel){
        BoxDTO boxDTO =  new BoxDTO(boxModel.getID(), boxModel.getName(), boxModel.getBasePrice(), boxModel.getContent(), boxModel.getDescription());
        boxDTO.setImage(boxModel.getImage());
        return boxDTO;
    }

    public BoxModel convertDTOToModel(BoxDTO boxDTO){
        return new BoxModel(boxDTO.getID(), boxDTO.getName(), boxDTO.getBasePrice(), boxDTO.getContent(), boxDTO.getDescription(), boxDTO.getImage());
    }
}
