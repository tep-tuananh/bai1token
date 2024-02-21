package ra.controller.admin;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Category>>getAll(){
        List<Category> categories= categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody @Valid Category category){
        Category categoryCreate=categoryService.save(category);
        return new ResponseEntity<>(categoryCreate,HttpStatus.CREATED);
    }
// tim theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> findId(@PathVariable Long id){
        Category category= categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }


    // update
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatecategory(@PathVariable Long id,@RequestBody Category category){
        Category categoryUpdate= categoryService.save(category);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.OK);
    }
    // xoa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>("Xoa thanh cong",HttpStatus.OK);
    }
}
