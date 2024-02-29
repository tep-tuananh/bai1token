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
import ra.model.dto.request.ProductRequest;
import ra.model.entity.Product;
import ra.service.admin.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAll( // xong 42
                                                 @RequestParam(defaultValue = "3", name = "limit") int limit,
                                                 @RequestParam(defaultValue = "0", name = "page") int page,
                                                 @RequestParam(defaultValue = "id", name = "sort") String sort

    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        Page<Product> productList = productService.getAll(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}") // xong 43
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tồn tại mã sản phẩm này!", HttpStatus.OK);
    }

    @PostMapping("/add") // xong 44
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product product = productRequest.add(productRequest);
        if (product != null) {
            productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>("Thêm sản phẩm thất bại!", HttpStatus.OK);
    }
}
