package fontys.sem3.smoke_it.Interfaces;

import fontys.sem3.smoke_it.model.BoxDTO;

import java.util.ArrayList;
import java.util.List;

public interface IDataSource {

    public List<BoxDTO> getAllBoxes();

    public List<BoxDTO> getAllBoxesSorted(String sort);

    public BoxDTO getBoxWithID(int ID);

    public boolean createBox(BoxDTO boxDTO);

    public boolean updateBox(BoxDTO boxDTO);

    public boolean deleteBox(int id);

    public double calculateBoxPrice(BoxDTO boxDTO, int amount);
}
