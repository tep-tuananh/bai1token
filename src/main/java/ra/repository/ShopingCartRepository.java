package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.model.entity.ShoppingCart;

import java.util.List;


@Repository
public interface ShopingCartRepository extends JpaRepository<ShoppingCart,Long> {
    List<ShoppingCart> findByUser(Long id);
}
