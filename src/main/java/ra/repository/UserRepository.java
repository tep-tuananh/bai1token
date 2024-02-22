package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.dto.reponse.UserReponse;
import ra.model.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
   Optional<User> findByUserName (String userName);
}
