package ra.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Category;
import ra.service.admin.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Page<Category>> getAll(@RequestParam(defaultValue = "3", name = "limit") int limit,
                                                 @RequestParam(defaultValue = "0", name = "page") int page,
                                                 @RequestParam(defaultValue = "id",name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of(page, limit,Sort.by(sort).ascending());
        Page<Category> categories = categoryService.getAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Valid Category category) {
        Category categoryCreate = categoryService.save(category);
        return new ResponseEntity<>(categoryCreate, HttpStatus.CREATED);
    }

    // tim theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatecategory(@PathVariable Long id, @RequestBody Category category) {
        Category categoryUpdate = categoryService.save(category);
        return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
    }

    // xoa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
    }
}
