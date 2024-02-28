package ra.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ra.model.entity.Address;
import ra.model.entity.User;
import ra.security.userDetailSecurity.UserPrincipal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressRequest {
   @NotNull(message = "Không để trống địa chỉ nhận hàng")
   @NotBlank(message = "Không để trống địa chỉ nhận hàng")
    private String fullAddress;
    @NotNull(message = "Không để trống sdt nhận hàng")
    @NotBlank(message = "Không để trống sdt nhận hàng")
    @Pattern(regexp = "0[0-9]{9}",message = "Khong dung dinh dang sdt")
    @Column(unique = true)
    private String phone;
    @NotBlank(message = "Khong de trong ten nguoi nhan")
    @NotNull(message = "Khong de trong ten nguoi nhan")
    private String name ;// nguoi nhan hang
    public Address add (AddressRequest addressRequest){
        this.fullAddress = addressRequest.fullAddress;
        this.phone= addressRequest.phone;
        this.name = addressRequest.name;
        return Address.builder()
                .user(User.builder().id(getUserId()).build())
                .full_address(this.fullAddress)
                .phone(this.phone)
                .receive_name(this.name)
                .build();
    }
    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }
}
