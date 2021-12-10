package fontys.sem3.smoke_it.repository.JPARepo;

import fontys.sem3.smoke_it.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {

    UserModel getFirstByUsername(String username);
}
