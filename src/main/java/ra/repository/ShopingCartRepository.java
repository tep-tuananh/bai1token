package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.Product;
import ra.model.entity.ShoppingCart;

import java.util.List;


@Repository
public interface ShopingCartRepository extends JpaRepository<ShoppingCart,Long> {
    // lấy ra tất cả dữ liệu của shoppingCart và product theo user_id
    @Query("SELECT s,p from ShoppingCart s JOIN Product p ON s.product.id = p.id where s.user.user_id = :id  ")
    List<ShoppingCart> findShoppingCartsByUserUser_idAndProductId(Long id);

    @Query("select s from  ShoppingCart s  where  s.user.user_id = :userid and s.id = :id")
    ShoppingCart findShoppingCartsById(Long id,Long userid);

}
