package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.repository.interfaces.IBoxSorter;
import fontys.sem3.smoke_it.repository.interfaces.IDataSource;
import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.model.BoxModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class FakeDataSource implements IDataSource, IBoxSorter {

    List<BoxModel> fakeBoxList = new ArrayList<>();


    public FakeDataSource(){
        fakeBoxList.add(new BoxModel("1", "Roll-Kit", 4.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every smoker out there"));
        fakeBoxList.add(new BoxModel("2", "Supply-Kit", 9.99, "Crusher, Pipe, Ashtray", "Ready for a small surprise every time? Includes Roll-Kit"));
        fakeBoxList.add(new BoxModel("3", "Smoke-Kit", 16.99, "Bong, Pipe, Glass work", "The perfect small kit for every"));
    }

    @Override
    public List<BoxModel> getAllBoxes(){
        return this.fakeBoxList;
    }

    @Override
    public List<BoxModel> getAllBoxesSorted(String sort){
        List<BoxModel> fakeBoxListSorted = new ArrayList<>();
        switch (sort){
            case "l-h":
                fakeBoxListSorted = boxesSortedLowToHigh(fakeBoxList);
                break;
            case "h-l":
                fakeBoxListSorted = boxesSortedHighToLow(fakeBoxList);
                break;
        }
        return fakeBoxListSorted;
    }

    @Override
    public BoxModel getBoxWithID(String ID){
        for(BoxModel b: fakeBoxList){
            if(b.getID() == ID){
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean createBox(BoxModel boxModel){
        if(getBoxWithID(boxModel.getID()) != null){
            return false;
        }

        fakeBoxList.add(boxModel);
        return true;
    }

    @Override
    public boolean updateBox(BoxModel boxModel){
        BoxModel oldBox = getBoxWithID(boxModel.getID());
        if(oldBox == null){
            return false;
        }
        oldBox.setName(boxModel.getName());
        oldBox.setBasePrice(boxModel.getBasePrice());
        oldBox.setContent(boxModel.getContent());
        oldBox.setDescription(boxModel.getDescription());
        return true;
    }

    @Override
    public boolean deleteBox(String id){
        BoxModel boxToDelete = getBoxWithID(id);
        if(boxToDelete == null){
            return false;
        }

        return fakeBoxList.remove(boxToDelete);
    }

    @Override
    public List<BoxModel> boxesSortedHighToLow(List<BoxModel> listToSort) {
        List<BoxModel> sortedList = listToSort;
        Collections.sort(sortedList, new Comparator<BoxModel>() {
            @Override
            public int compare(BoxModel b1, BoxModel b2) {
                return Double.compare(b1.getBasePrice(), b2.getBasePrice());
            }
        });
        return sortedList;
    }

    @Override
    public List<BoxModel> boxesSortedLowToHigh(List<BoxModel> listToSort) {
        List<BoxModel> sortedList = listToSort;
        Collections.sort(sortedList, new Comparator<BoxModel>() {
            @Override
            public int compare(BoxModel b1, BoxModel b2) {
                return Double.compare(b2.getBasePrice(), b1.getBasePrice());
            }
        });
        return sortedList;
    }
}
