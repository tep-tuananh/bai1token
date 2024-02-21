package ra.service.admin.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.dto.reponse.CategoryReponse;
import ra.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category save(Category category);
    Category findById(Long id);
    void deleteById(Long id);
}
