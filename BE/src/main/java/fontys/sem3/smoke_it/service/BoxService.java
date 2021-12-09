package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.repository.interfaces.IBoxSorter;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class BoxService implements IBoxService , IBoxSorter {

    @Autowired
    IDataSourceBoxes datasource;

    @Override
    public List<BoxModel> getAllBoxes() {
        return datasource.getAllBoxes();
    }

    @Override
    public List<BoxModel> getAllBoxesSorted(String sort){
        List<BoxModel> listToReturn = datasource.getAllBoxes();
        switch (sort) {
            case "l-h":
                return boxesSortedLowToHigh(listToReturn);
            case "h-l":
                return boxesSortedHighToLow(listToReturn);
            default:
                return listToReturn;
        }
    }

    @Override
    public BoxModel getBoxWithID(String ID){
        return datasource.getBoxWithID(ID);
    }

    @Override
    public boolean createBox(BoxModel boxModel){
        return datasource.createBox(boxModel);
    }
    
    @Override
    public boolean updateBox(BoxModel boxModel){
        if(boxModel != null){
            return datasource.updateBox(boxModel);
        }
        return false;
    }

    @Override
    public boolean deleteBox(String ID){
       return datasource.deleteBox(ID);
    }

    @Override
    public double calculateBoxPrice(BoxModel boxModel, int amount){
        if(amount > 1){
            double basePrice = boxModel.getBasePrice();
            double divider = 1 - 0.02*amount;
            return basePrice * divider;
        }
        return boxModel.getBasePrice();
    }

    @Override
    public String createID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<BoxModel> boxesSortedHighToLow(List<BoxModel> listToSort) {
        List<BoxModel> list = listToSort;
        Collections.sort(list, new Comparator<BoxModel>() {
            @Override
            public int compare(BoxModel b1, BoxModel b2) {
                return Double.compare(b2.getBasePrice(), b1.getBasePrice());
            }
        });
        return listToSort;
    }

    @Override
    public List<BoxModel> boxesSortedLowToHigh(List<BoxModel> listToSort) {
        Collections.sort(listToSort, new Comparator<BoxModel>() {
            @Override
            public int compare(BoxModel b1, BoxModel b2) {
                return Double.compare(b1.getBasePrice(), b2.getBasePrice());
            }
        });
        return listToSort;
    }

}
