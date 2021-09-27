package fontys.sem3.smoke_it.service;

import fontys.sem3.smoke_it.repository.interfaces.IDataSource;
import fontys.sem3.smoke_it.model.BoxDTO;
import fontys.sem3.smoke_it.service.interfaces.IBoxService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoxService implements IBoxService {

    IDataSource datasource;

    public BoxService(IDataSource datasource){
        this.datasource = datasource;
    }

    @Override
    public List<BoxDTO> getAllBoxes(){
        return datasource.getAllBoxes();
    }

    @Override
    public List<BoxDTO> getAllBoxesSorted(String sort){
        return datasource.getAllBoxesSorted(sort);
    }

    @Override
    public BoxDTO getBoxWithID(int ID){
        return datasource.getBoxWithID(ID);
    }

    @Override
    public boolean createBox(BoxDTO boxDTO){
        return datasource.createBox(boxDTO);
    }

    @Override
    public boolean updateBox(BoxDTO boxDTO){
        return datasource.updateBox(boxDTO);
    }

    @Override
    public boolean deleteBox(int ID){
       return datasource.deleteBox(ID);
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

}
