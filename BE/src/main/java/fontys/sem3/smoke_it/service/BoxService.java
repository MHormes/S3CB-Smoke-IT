package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.repository.interfaces.IBoxSorter;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class BoxService implements IBoxService , IBoxSorter {


    IDataSourceBoxes datasource;

    @Autowired
    public BoxService(IDataSourceBoxes datasource){
        this.datasource = datasource;
    }

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
    public BoxModel getBoxWithID(String id){
        return datasource.getBoxWithID(id);
    }

    @Override
    public BoxModel createBox(BoxModel boxModel){
        return datasource.createBox(boxModel);
    }
    
    @Override
    public BoxModel updateBox(BoxModel boxModel){
        if(boxModel != null){
            return datasource.updateBox(boxModel);
        }
        return null;
    }

    @Override
    public boolean deleteBox(String id){
       return datasource.deleteBox(id);
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
        Collections.sort(list, (b1, b2) -> Double.compare(b2.getBasePrice(), b1.getBasePrice()));
        return listToSort;
    }

    @Override
    public List<BoxModel> boxesSortedLowToHigh(List<BoxModel> listToSort) {
        Collections.sort(listToSort, Comparator.comparingDouble(BoxModel::getBasePrice));
        return listToSort;
    }

}
