package fontys.sem3.smoke_it.Interfaces;

import fontys.sem3.smoke_it.model.BoxDTO;

import java.util.List;

public interface IBoxSorter {

    public List<BoxDTO> boxesSortedHighToLow(List<BoxDTO> listToSort);

    public List<BoxDTO> boxesSortedLowToHigh(List<BoxDTO> listToSort);
}
