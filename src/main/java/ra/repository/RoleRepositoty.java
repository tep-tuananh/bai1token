package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Role;

public interface RoleRepositoty extends JpaRepository<Role,Long> {
    Role findByName (String name);
}
