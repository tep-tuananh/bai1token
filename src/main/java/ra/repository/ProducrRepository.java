package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;

import java.util.List;

@Repository
public interface ProducrRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductName(String productName);
    List<Product> findByDescription(String description);
    List<Product> findByCategory_Id(Long id);
}
