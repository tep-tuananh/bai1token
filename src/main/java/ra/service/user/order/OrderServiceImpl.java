package ra.service.user.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.request.OrderRequest;
import ra.model.entity.Orders;
import ra.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrserService  {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders save(OrderRequest orderRequest) {
        return orderRepository.save(orderRequest.add(orderRequest));
    }
}
