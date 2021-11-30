package fontys.sem3.smoke_it.repository.interfaces;

import fontys.sem3.smoke_it.model.NewsMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository<NewsMessageModel, Long> {
}
