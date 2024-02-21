package ra.service.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.model.dto.reponse.CategoryReponse;
import ra.model.entity.Category;
import ra.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImol implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
