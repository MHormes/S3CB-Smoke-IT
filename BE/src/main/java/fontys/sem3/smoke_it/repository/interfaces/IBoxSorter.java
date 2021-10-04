package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.BoxModel;

import java.util.List;

public interface IBoxSorter {

    public List<BoxModel> boxesSortedHighToLow(List<BoxModel> listToSort);

    public List<BoxModel> boxesSortedLowToHigh(List<BoxModel> listToSort);
}
