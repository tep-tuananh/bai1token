package ra.service.user.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.entity.Orders;
import ra.model.entity.StatusEnum;
import ra.repository.OrderRepository;

import java.util.List;
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Orders> getAll(Long id) {
        return orderRepository.findOrdersByUserId(id);
    }

    @Override
    public List<Orders> getOrderStatus(String status, Long id) {
        StatusEnum statusEnum = StatusEnum.valueOf(status.toUpperCase());
        return orderRepository.findOrdersByStatusAndUserId(statusEnum,id);
    }

    @Override
    public Orders findId(Long id,Long userId) {
        return orderRepository.findOrdersByIdAndUserId(id,userId);
    }



    @Override
    public Orders getOrderBySerial(Long serial,Long id) {
        return orderRepository.findOrdersBySerialNumberAndUserId(serial,id);
    }
}
