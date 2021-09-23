package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.Interfaces.IBoxSorter;
import fontys.sem3.smoke_it.Interfaces.IDataSource;
import fontys.sem3.smoke_it.model.BoxDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FakeDataSource implements IDataSource, IBoxSorter {

    List<BoxDTO> fakeBoxList = new ArrayList<>();


    public FakeDataSource(){
        fakeBoxList.add(new BoxDTO(1, "Roll-Kit", 4.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every smoker out there"));
        fakeBoxList.add(new BoxDTO(2, "Supply-Kit", 9.99, "Crusher, Pipe, Ashtray", "Ready for a small surprise every time? Includes Roll-Kit"));
        fakeBoxList.add(new BoxDTO(3, "Smoke-Kit", 16.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every"));
    }

    @Override
    public List<BoxDTO> getAllBoxes(){
        return this.fakeBoxList;
    }

    public List<BoxDTO> getAllBoxesSorted(String sort){
        List<BoxDTO> fakeBoxListSorted = new ArrayList<>();
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
    public BoxDTO getBoxWithID(int ID){
        for(BoxDTO b: fakeBoxList){
            if(b.getID() == ID){
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean createBox(BoxDTO boxDTO){
        if(getBoxWithID(boxDTO.getID()) != null){
            return false;
        }

        fakeBoxList.add(boxDTO);
        return true;
    }

    @Override
    public boolean updateBox(BoxDTO boxDTO){
        BoxDTO oldBox = getBoxWithID(boxDTO.getID());
        if(oldBox == null){
            return false;
        }
        oldBox.setName(boxDTO.getName());
        oldBox.setBasePrice(boxDTO.getBasePrice());
        oldBox.setContent(boxDTO.getContent());
        oldBox.setDescription(boxDTO.getDescription());
        return true;
    }

    @Override
    public boolean deleteBox(int id){
        BoxDTO boxToDelete = getBoxWithID(id);
        if(boxToDelete == null){
            return false;
        }

        return fakeBoxList.remove(boxToDelete);
    }

    @Override
    public double calculateBoxPrice(BoxDTO boxDTO, int amount){
        if(amount > 1){
            double basePrice = boxDTO.getBasePrice();
            double divider = 1 - 0.02*amount;
            return basePrice * divider;
        }
        return boxDTO.getBasePrice();
    }

    @Override
    public List<BoxDTO> boxesSortedHighToLow(List<BoxDTO> listToSort) {
        List<BoxDTO> sortedList = listToSort;
        Collections.sort(sortedList, new Comparator<BoxDTO>() {
            @Override
            public int compare(BoxDTO b1, BoxDTO b2) {
                return Double.compare(b1.getBasePrice(), b2.getBasePrice());
            }
        });
        return sortedList;
    }

    @Override
    public List<BoxDTO> boxesSortedLowToHigh(List<BoxDTO> listToSort) {
        List<BoxDTO> sortedList = listToSort;
        Collections.sort(sortedList, new Comparator<BoxDTO>() {
            @Override
            public int compare(BoxDTO b1, BoxDTO b2) {
                return Double.compare(b2.getBasePrice(), b1.getBasePrice());
            }
        });
        return sortedList;
    }
}
