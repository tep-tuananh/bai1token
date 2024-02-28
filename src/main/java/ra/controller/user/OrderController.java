package ra.controller.user;

import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Orders;
import ra.model.entity.StatusEnum;
import ra.repository.OrderRepository;
import ra.security.userDetailSecurity.UserPrincipal;
import ra.service.user.history.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/v1/user/history")
public class OrderController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("") // xong 29
    public ResponseEntity<List<Orders>> getAllHistory() {
        List<Orders> orders = historyService.getAll(getUserId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{seriaNumber}") // xong 30
    public ResponseEntity<?> getOrderSerial(@PathVariable long seriaNumber) {
        Orders ordersList = historyService.getOrderBySerial(seriaNumber, getUserId());
        if (ordersList != null) {
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Không tồn tại mã seria này trong lịch sử mua hàng", HttpStatus.OK);
        }
    }

    @GetMapping("/status/{status}") // xong 31
    public ResponseEntity<?> getOrderStatus(@PathVariable("status") String status) {
        try {
            List<Orders> ordersList = historyService.getOrderStatus(String.valueOf(StatusEnum.valueOf(status.toUpperCase())), getUserId());
            if (ordersList != null) {
                return new ResponseEntity<>(ordersList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Không tồn tại đơn hàng trạng thái này!" + status, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Trạng thái không hợp lệ" + status, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}/canel") // xong 32
    public ResponseEntity<?> update_cancel(@PathVariable Long id) {
        Orders orders = historyService.findId(id, getUserId());
        if (orders != null) {
            String status = String.valueOf(orders.getStatus());
            if (status.equals("CANCEL")) {
                return new ResponseEntity<>("Đơn hàng này đã bị hủy từ trước ", HttpStatus.OK);
            } else if (status.equals("WAITING")) {
                orders.setStatus(StatusEnum.valueOf("CANCEL"));
                orderRepository.save(orders);
                return new ResponseEntity<>(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Đơn hàng đã duyệt không thể hủy! ", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Mã không tồn tại!", HttpStatus.OK);
    }


    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }
}
