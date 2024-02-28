package ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ra.model.entity.Orders;
import ra.model.entity.StatusEnum;
import ra.model.entity.User;
import ra.security.userDetailSecurity.UserPrincipal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderRequest {
    @NotNull(message = "Không để trống địa chỉ nhận hàng")
    @NotBlank(message = "Khong dc null")
    private String userNamee;
    @NotNull(message = "Không để trống địa chỉ nhận hàng")
    @NotBlank(message = "Khong dc null")
    private String note;
    @NotNull(message = "Không để trống địa chỉ nhận hàng")
    @NotBlank(message = "Khong dc null")
    private String address;
    @NotNull(message = "Khong duoc bo trong")
    @Pattern(regexp = "0[0-9]{9}", message = "Khong dung dinh dang sdt")
    private String phoneOder;
    @NotNull(message = "Khong duoc de trong")
    @NotBlank(message = "Khong duoc de trong")
    private StatusEnum status;
    public Orders add(OrderRequest orderRequest){
        this.note = orderRequest.note;
        this.address = orderRequest.getAddress();
        this.phoneOder = orderRequest.phoneOder;
        this.status = orderRequest.getStatus();
        return Orders.builder()
                .user(User.builder().id(getUserId()).build())
                .userName(userNamee)
                .note(note)
                .address(address)
                .phoneOder(phoneOder)
                .build();
    }
    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }
}
