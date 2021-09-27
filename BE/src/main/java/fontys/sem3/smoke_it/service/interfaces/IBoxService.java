package fontys.sem3.smoke_it.service.interfaces;

import fontys.sem3.smoke_it.model.BoxDTO;

import java.util.List;

public interface IBoxService {

    public List<BoxDTO> getAllBoxes();

    public List<BoxDTO> getAllBoxesSorted(String sort);

    public BoxDTO getBoxWithID(int ID);

    public boolean createBox(BoxDTO boxDTO);

    public boolean updateBox(BoxDTO boxDTO);

    public boolean deleteBox(int ID);

    public double calculateBoxPrice(BoxDTO boxDTO, int amount);
}
