package ra.service.user.shoppingcart;

import ra.model.entity.ShoppingCart;
import ra.model.entity.User;

import java.util.List;

public interface ShopingCartService {
    List<ShoppingCart> getAll(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
    ShoppingCart findById(Long id);
    void deleteById(Long id);
}
