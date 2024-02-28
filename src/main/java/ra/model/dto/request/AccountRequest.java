package ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountRequest {
    @NotNull(message = "Không để trống địa chỉ nhận hàng")
    @NotBlank(message = "Khong dc null")
    private String fullName;
    @NotNull(message = "Khong duoc de trong")
    @NotBlank(message = "Khong dc null")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "không đúng định dạng")
    private String email;
    @NotNull(message = "Không để trống sdt nhận hàng")
    @Pattern(regexp = "0[0-9]{9}", message = "Khong dung dinh dang sdt")
    @NotBlank(message = "Khong dc null")
    private String phone;
    @NotNull(message = "Khong dc trong")
    @NotBlank(message = "Khong dc null")
    private String address;

    public User updateAccount(AccountRequest accountRequest, User user) {
    user.setFullName(accountRequest.getFullName());
    user.setEmail(accountRequest.getEmail());
    user.setPhone(accountRequest.getPhone());
    user.setAddress(accountRequest.getAddress());
    return user;
    }
}
