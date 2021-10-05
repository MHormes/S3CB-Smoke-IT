package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.BoxModel;

import java.util.List;

public interface IBoxService {

    public List<BoxModel> getAllBoxes();

    public List<BoxModel> getAllBoxesSorted(String sort);

    public BoxModel getBoxWithID(String ID);

    public boolean createBox(BoxModel boxModel);

    public boolean updateBox(BoxModel boxModel);

    public boolean deleteBox(String ID);

    public double calculateBoxPrice(BoxModel boxModel, int amount);

    public String createID();
}
