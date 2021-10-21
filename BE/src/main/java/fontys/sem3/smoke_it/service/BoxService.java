package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoxService implements IBoxService {

    IDataSourceBoxes datasource;
    @Autowired
    public BoxService(@Qualifier("dataSourceBoxes") IDataSourceBoxes datasource){
        this.datasource = datasource;
    }

    @Override
    public List<BoxModel> getAllBoxes() {
        List<BoxModel> boxList = datasource.getAllBoxes();
        return boxList;
    }

    @Override
    public List<BoxModel> getAllBoxesSorted(String sort){
        return datasource.getAllBoxesSorted(sort);
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
        return datasource.updateBox(boxModel);
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

}
