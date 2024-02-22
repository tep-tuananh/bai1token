package ra.controller.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.reponse.UserReponse;
import ra.model.dto.request.UserLoginRequest;
import ra.model.entity.Category;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.repository.ProducrRepository;
import ra.service.admin.category.CategoryService;
import ra.service.admin.product.ProductService;
import ra.service.auth.UserService;

import java.util.List;

@RestController
@RequestMapping("/v1/auth/")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProducrRepository producrRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid User user) {
        User userNew = userService.register(user);
        if (user != null) {
            return new ResponseEntity<>("dang ky thanh cong", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("dang ky that bai", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest userLogin) {
        UserReponse userReponse = userService.login(userLogin);
        return new ResponseEntity<>(userReponse, HttpStatus.OK);
    }

    //lay ra danh muc
    @GetMapping("/categories") // xong
    public ResponseEntity<Page<Category>> getAll(@RequestParam(defaultValue = "3", name = "limit") int limit,
                                                 @RequestParam(defaultValue = "0", name = "page") int page,
                                                 @RequestParam(defaultValue = "id", name = "sort") String sort
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        Page<Category> categories = categoryService.getAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // tim kiem theo ten san pham
    @GetMapping("/product/{search}") // xong
    public ResponseEntity<List<Product>> findName(@PathVariable String search) {
        List<Product> productList = producrRepository.findByProductName(search);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // tim kiem theo san pham noi bat
    @GetMapping("/product/featured-products")
    public ResponseEntity<List<Product>> get_featured_products(String decription) {
        decription = "noi bat";
        List<Product> productList = producrRepository.findByDescription(decription);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // hien thi tat ca cac san pham
    @GetMapping("/products") // xong
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> productList = producrRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // cac san pham moi
    @GetMapping("/product/new-product") // xong
    public ResponseEntity<List<Product>> getAll(String decription) {
        decription = "hang moi";
        List<Product> productList = producrRepository.findByDescription(decription);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // tim kiem san pham ban chay
    @GetMapping("/product/best-seller-product") // xong
    public ResponseEntity<List<Product>> get_best_product(String disription) {
        disription = "ban chay";
        List<Product> productList = producrRepository.findByDescription(disription);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // tim kiem san pham theo ma danh muc
    @GetMapping("/product/category/{id}")
    public ResponseEntity<List<Product>> get_product_categoryId(@PathVariable Long id) {
        List<Product> productList = producrRepository.findByCategory_Id(id);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    // tim kiem san pham theo id san pham
    @GetMapping("/product/find/{id}")
    public ResponseEntity<?> get_Product_id (@PathVariable Long id) {
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
