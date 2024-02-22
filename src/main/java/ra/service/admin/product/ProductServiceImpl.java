package ra.service.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.entity.Product;
import ra.repository.ProducrRepository;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProducrRepository producrRepository;
    @Override
    public Page<Product> getAll(Pageable pageable) {
        Page<Product> products = producrRepository.findAll(pageable);
        return products;
    }

    @Override
    public Product findById(Long id) {
        return producrRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return producrRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        producrRepository.deleteById(id);
    }

    @Override
    public List<Product> findByName(String productName) {
        return producrRepository.findByProductName(productName);
    }
}
