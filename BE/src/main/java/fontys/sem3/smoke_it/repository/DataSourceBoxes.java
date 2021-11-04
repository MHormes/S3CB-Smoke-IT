package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.interfaces.IBoxSorter;
import fontys.sem3.smoke_it.repository.interfaces.IBoxesRepository;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class DataSourceBoxes implements IDataSourceBoxes, IBoxSorter {

    @Autowired
    IBoxesRepository repo;

    @Override
    public List<BoxModel> getAllBoxes() {
        return repo.findAll();
    }

    @Override
    public List<BoxModel> getAllBoxesSorted(String sort){
        switch (sort){
            case "l-h":
                return boxesSortedLowToHigh(repo.findAll());
            case "h-l":
                return boxesSortedHighToLow(repo.findAll());
            default: return repo.findAll();
        }
    }

    @Override
    public BoxModel getBoxWithID(String id) {
        return repo.getOne(id);
    }

    @Override
    public boolean createBox(BoxModel boxModel) {
        repo.save(boxModel);
        return true;
    }

    @Override
    public boolean updateBox(BoxModel boxModel) {
        BoxModel modelToUpdate = repo.getOne(boxModel.getId());
        if(modelToUpdate.getName() != null){
            modelToUpdate.setName(boxModel.getName());
            modelToUpdate.setBasePrice(boxModel.getBasePrice());
            modelToUpdate.setContent(boxModel.getContent());
            modelToUpdate.setDescription(boxModel.getDescription());
            if(boxModel.getImagePath() != null){
                modelToUpdate.setImagePath(boxModel.getImagePath());
            }
            repo.save(modelToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBox(String id) {
        BoxModel modelToDelete = repo.getOne(id);
        if(modelToDelete.getName() != null){
            repo.deleteById(id);
            return true;
        }
        return false;
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
