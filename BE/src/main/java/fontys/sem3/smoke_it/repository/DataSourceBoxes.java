package fontys.sem3.smoke_it.repository;

import fontys.sem3.smoke_it.model.BoxModel;
import fontys.sem3.smoke_it.repository.JPARepo.IBoxesRepository;
import fontys.sem3.smoke_it.repository.interfaces.IDataSourceBoxes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DataSourceBoxes implements IDataSourceBoxes {

    @Autowired
    IBoxesRepository repo;

    @Override
    public List<BoxModel> getAllBoxes() {
        return repo.findAll();
    }

    @Override
    public BoxModel getBoxWithID(String id) {
        Optional<BoxModel> boxModel = repo.findById(id);
        return boxModel.orElse(null);
    }

    @Override
    public BoxModel createBox(BoxModel boxModel) {
        return repo.save(boxModel);

    }

    @Override
    public BoxModel updateBox(BoxModel boxModel) {
        BoxModel modelToUpdate = repo.getBoxModelById(boxModel.getId());
        if (modelToUpdate != null) {
            modelToUpdate.setName(boxModel.getName());
            modelToUpdate.setBasePrice(boxModel.getBasePrice());
            modelToUpdate.setContent(boxModel.getContent());
            modelToUpdate.setDescription(boxModel.getDescription());
            if (!Objects.equals(boxModel.getImagePath(), "")) {
                modelToUpdate.setImagePath(boxModel.getImagePath());
            }
            return repo.save(modelToUpdate);
        }
        return null;

    }

    @Override
    public boolean deleteBox(String id) {
        Optional<BoxModel> modelToDelete = repo.findById(id);
        if (modelToDelete.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
