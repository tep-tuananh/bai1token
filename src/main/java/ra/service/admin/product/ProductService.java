package ra.service.admin.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(Pageable pageable);
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    List<Product> findByName(String productName);
}
