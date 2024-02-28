package ra.service.admin.category;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Category;


public interface CategoryService {
    Page<Category> getAll(Pageable pageable);
    Category save(Category category);
    Category findById(Long id);
    void deleteById(Long id);
}
