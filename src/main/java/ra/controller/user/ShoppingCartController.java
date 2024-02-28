package ra.controller.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.AddressRequest;
import ra.model.dto.request.ShoppingCartRequest;
import ra.model.entity.Address;
import ra.model.entity.ShoppingCart;
import ra.security.userDetailSecurity.UserPrincipal;
import ra.service.admin.product.ProductService;
import ra.service.auth.UserService;
import ra.service.user.address.AddressService;
import ra.service.user.shoppingcart.ShopingCartService;

import java.util.List;

@RestController
@RequestMapping("/v1/user/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShopingCartService shopingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AddressService addressService;

    // lay danh sach shoping cart theo id nguoi dang nhap
    @GetMapping("") // xong 16
    public ResponseEntity<List<ShoppingCart>> getAll() {
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts = shopingCartService.getAll(userId);
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    // them san pham vao gio hang
    @PostMapping("/add") // xong 17
    public ResponseEntity<?> add(@RequestBody @Valid ShoppingCartRequest shoppingCartRequest) {
        ShoppingCart shoppingCart = shopingCartService.add(shoppingCartRequest);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    // ttìm kiếm mã shoppingcartId có trong giỏ hàng không
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shopingCartService.findById(id, getUserId());
        if (shoppingCart == null) {
            return new ResponseEntity<>("Không tồn tại mã này! ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        }
    }

    // câp nhật
    @PutMapping("/{id}") // xong 18
    public ResponseEntity<ShoppingCart> update(@PathVariable Long id, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setProduct(productService.save(shoppingCart.getProduct()));
        shoppingCart.setUser(userService.findById(getUserId()));
        shoppingCart.setQuantity(shoppingCart.getQuantity());
        ShoppingCart cart = shopingCartService.save(shoppingCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    //
    @DeleteMapping("/{id}") // xong 19
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shopingCartService.findById(id, getUserId());
        if (shoppingCart != null) {
            shopingCartService.deleteById(id);
        } else {
            return new ResponseEntity<>("Không tồn tại mã này!", HttpStatus.OK);
        }
        return null;
    }

    // xóa toàn bộ sản phẩm trong giỏ hàng 20
    @DeleteMapping("")
    public ResponseEntity<?> deleteAll() {
        List<ShoppingCart> shoppingCarts =shopingCartService.getAll(getUserId());// lấy toàn bộ danh sách sản phẩm theo id đăn nhập
        shoppingCarts.forEach(shoppingCart -> shopingCartService.deleteById(shoppingCart.getId())); // vòng lặp để xóa theo id shopingcart
        return new ResponseEntity<>("Xóa toàn bộ sản phẩm trong giỏ hàng thành công", HttpStatus.OK);
    }
    @PostMapping("/checkout") // xong 21
    public ResponseEntity<?> checkout(@RequestBody @Valid AddressRequest addressRequest){
        Address address = addressService.save(addressRequest);
        return new ResponseEntity<>(address,HttpStatus.CREATED);
    }
}

