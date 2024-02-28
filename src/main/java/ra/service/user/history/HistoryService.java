package ra.service.user.history;

import ra.model.entity.Orders;

import java.util.List;

public interface HistoryService {
    List<Orders> getAll(Long id);
    Orders getOrderBySerial(Long serial,Long id);
    List<Orders> getOrderStatus(String status, Long id);
    Orders findId(Long id,Long userId);
}
