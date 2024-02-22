package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ra.model.entity.Product;
import ra.service.admin.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public ResponseEntity<Page<Product>> getAll(
            @RequestParam(defaultValue = "3",name = "limit") int limit,
            @RequestParam(defaultValue = "0",name = "page") int page,
            @RequestParam(defaultValue = "id",name = "sort") String sort

    ){
        Pageable pageable= PageRequest.of(page,limit,Sort.by(sort).ascending());
        Page<Product> productList = productService.getAll(pageable);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
