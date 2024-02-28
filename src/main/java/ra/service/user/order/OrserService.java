package ra.service.user.order;

import ra.model.dto.request.OrderRequest;
import ra.model.entity.Orders;

public interface OrserService {
    Orders save(OrderRequest orderRequest);
}
