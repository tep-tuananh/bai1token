package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Role;
@Repository
public interface RoleRepositoty extends JpaRepository<Role,Long> {
    Role findByName (String name);
}
