package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.Interfaces.IBoxSorter;
import fontys.sem3.smoke_it.model.BoxDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FakeDataSource implements IBoxSorter {

    List<BoxDTO> fakeBoxList = new ArrayList<>();

    public FakeDataSource(){
        fakeBoxList.add(new BoxDTO(1, "Roll-Kit", 4.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every smoker out there"));
        fakeBoxList.add(new BoxDTO(2, "Supply-Kit", 9.99, "Crusher, Pipe, Ashtray", "Ready for a small surprise every time? Includes Roll-Kit"));
        fakeBoxList.add(new BoxDTO(3, "Smoke-Kit", 16.99, "Papers, Blunts, Joint-Tube", "The perfect small kit for every"));
    }

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

    public BoxDTO getBoxWithID(int ID){
        for(BoxDTO b: fakeBoxList){
            if(b.getID() == ID){
                return b;
            }
        }
        return null;
    }

    public boolean createBox(BoxDTO boxDTO){
        if(getBoxWithID(boxDTO.getID()) != null){
            return false;
        }

        fakeBoxList.add(boxDTO);
        return true;
    }

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

    public boolean deleteBox(int id){
        BoxDTO boxToDelete = getBoxWithID(id);
        if(boxToDelete == null){
            return false;
        }

        return fakeBoxList.remove(boxToDelete);
    }

    @Override
    public List<BoxDTO> boxesSortedHighToLow(List<BoxDTO> listToSort) {
        List<BoxDTO> sortedList = new ArrayList<>();

        return sortedList;
    }

    @Override
    public List<BoxDTO> boxesSortedLowToHigh(List<BoxDTO> listToSort) {
        List<BoxDTO> sortedList = new ArrayList<>();

        return sortedList;
    }
}