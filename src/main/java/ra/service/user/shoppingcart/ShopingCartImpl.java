package ra.service.user.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ra.model.dto.request.ShoppingCartRequest;
import ra.model.entity.Product;
import ra.model.entity.ShoppingCart;
import ra.repository.ShopingCartRepository;
import ra.security.userpincipal.UserPrincipal;
import ra.service.admin.product.ProductService;
import ra.service.auth.UserService;

import java.util.List;

@Service
public class ShopingCartImpl implements ShopingCartService {

    @Autowired
    private ShopingCartRepository shopingCartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getUser_id();
    }

    @Override
    public List<ShoppingCart> getAll(Long userid) {
        return shopingCartRepository.findShoppingCartsByUserUser_idAndProductId(userid);
    }

    @Override
    public ShoppingCart add(ShoppingCartRequest shoppingCartRequest) {
        ShoppingCart shoppingCart = shoppingCartRequest.add(shoppingCartRequest);
        return shopingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shopingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id,Long userId) {
        ShoppingCart cart = shopingCartRepository.findShoppingCartsById(id,userId);
        if (cart != null) {
            return cart;
        }else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        shopingCartRepository.deleteById(id);
    }
}
