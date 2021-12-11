package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.BoxModel;

import java.util.List;

public interface IDataSourceBoxes {

     List<BoxModel> getAllBoxes();

     BoxModel getBoxWithID(String id);

     BoxModel createBox(BoxModel boxModel);

     BoxModel updateBox(BoxModel boxModel);

     boolean deleteBox(String id);
}
