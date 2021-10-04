package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.BoxModel;

import java.util.ArrayList;
import java.util.List;

public interface IDataSource {

    List<BoxModel> fakeBoxList = new ArrayList<>();

    public List<BoxModel> getAllBoxes();

    public List<BoxModel> getAllBoxesSorted(String sort);

    public BoxModel getBoxWithID(String ID);

    public boolean createBox(BoxModel boxModel);

    public boolean updateBox(BoxModel boxModel);

    public boolean deleteBox(String id);
}
