package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.Orders;
import ra.model.entity.StatusEnum;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findOrdersByUserId(Long id);
   Orders findOrdersBySerialNumberAndUserId(Long serialNumber,Long id);
  //@Query("select o from  Orders  o where o.status = :status and o.user.id =:id ")
   List<Orders> findOrdersByStatusAndUserId(StatusEnum status, Long id);
   Orders findOrdersByIdAndUserId(Long id,Long userId);
}