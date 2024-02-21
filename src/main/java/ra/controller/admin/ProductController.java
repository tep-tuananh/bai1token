package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<Product>> getAll(){
        List<Product> productList = productService.getAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
