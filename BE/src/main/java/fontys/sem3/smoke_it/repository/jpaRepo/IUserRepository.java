package fontys.sem3.smoke_it.repository.jpaRepo;

import fontys.sem3.smoke_it.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel, Long> {

    UserModel getFirstByUsername(String username);
}
