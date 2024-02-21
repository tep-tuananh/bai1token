package ra.service.admin.product;

import ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
