package ra.service.user.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.ShoppingCart;
import ra.repository.ShopingCartRepository;

import java.util.List;
@Service
public class ShopingCartImpl implements  ShopingCartService{
    @Autowired
    private ShopingCartRepository shopingCartRepository;
    @Override
    public List<ShoppingCart> getAll(Long id) {
        return shopingCartRepository.findByUser(id);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shopingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shopingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        shopingCartRepository.deleteById(id);
    }
}
