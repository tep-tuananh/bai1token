package ra.service.user.shoppingcart;

import ra.model.dto.request.ShoppingCartRequest;
import ra.model.entity.Product;
import ra.model.entity.ShoppingCart;

import java.util.List;

public interface ShopingCartService {
    List<ShoppingCart> getAll(Long userid);
    ShoppingCart add(ShoppingCartRequest shoppingCartRequest); // thêm mới giỏ hàng
    ShoppingCart save(ShoppingCart shoppingCart);
    ShoppingCart findById(Long id,Long userId);
    void deleteById(Long id);
}
