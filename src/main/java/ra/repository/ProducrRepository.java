package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Product;

public interface ProducrRepository extends JpaRepository<Product,Long> {
}
