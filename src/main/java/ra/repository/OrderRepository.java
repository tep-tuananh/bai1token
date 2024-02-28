package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
