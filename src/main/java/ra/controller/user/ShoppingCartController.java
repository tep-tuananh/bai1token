package ra.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.model.entity.ShoppingCart;
import ra.security.userpincipal.UserPrincipal;
import ra.service.user.shoppingcart.ShopingCartService;

import java.util.List;

@RestController
@RequestMapping("/v1/user/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShopingCartService shopingCartService;
    // lay danh sach shoping cart theo id nguoi dang nhap
    @GetMapping("")
    public ResponseEntity<List<ShoppingCart>> getAll(){
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts= shopingCartService.getAll(userId);
        return  new ResponseEntity<>(shoppingCarts,HttpStatus.OK);
    }
    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getUser_id();
    }
}
