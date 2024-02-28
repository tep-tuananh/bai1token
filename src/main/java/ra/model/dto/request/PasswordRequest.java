package ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PasswordRequest {
    @NotNull(message = "không được để trống mật khẩu")
    @NotBlank(message = "không được để trống mật khẩu")
    private String oldPassword;
    @NotNull(message = "không được để trống mật khẩu")
    @NotBlank(message = "Hãy nhập mật khẩu mới")
    public String newPassword;
    @NotNull(message = "không được để trống mật khẩu")
    @NotBlank(message = "Hãy nhập vào mật khẩu mới vừa nhâp")
    private String confirmNewPassword;
}
