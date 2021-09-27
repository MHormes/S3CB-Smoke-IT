package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.BoxDTO;

import java.util.ArrayList;
import java.util.List;

public interface IDataSource {

    List<BoxDTO> fakeBoxList = new ArrayList<>();

    public List<BoxDTO> getAllBoxes();

    public List<BoxDTO> getAllBoxesSorted(String sort);

    public BoxDTO getBoxWithID(int ID);

    public boolean createBox(BoxDTO boxDTO);

    public boolean updateBox(BoxDTO boxDTO);

    public boolean deleteBox(int id);
}
