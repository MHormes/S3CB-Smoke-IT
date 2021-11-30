package fontys.sem3.smoke_it.repository.jpaRepo;

import fontys.sem3.smoke_it.model.BoxModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBoxesRepository extends JpaRepository<BoxModel, String> {
}
