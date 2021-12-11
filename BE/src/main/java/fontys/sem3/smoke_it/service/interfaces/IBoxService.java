package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.BoxModel;

import java.util.List;

public interface IBoxService {

     List<BoxModel> getAllBoxes();

     List<BoxModel> getAllBoxesSorted(String sort);

     BoxModel getBoxWithID(String id);

     BoxModel createBox(BoxModel boxModel);

     BoxModel updateBox(BoxModel boxModel);

     boolean deleteBox(String id);

     double calculateBoxPrice(BoxModel boxModel, int amount);

     String createID();
}
