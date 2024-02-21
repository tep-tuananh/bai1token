package ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotNull(message ="khong duong null")
    @NotBlank(message = "khong duoc trong")
    @NotBlank(message = "khong duoc de trong")
    private String userName;
    @NotNull(message ="khong duong null")
    @NotBlank(message = "khong duoc trong")
    @NotBlank(message = "khong duoc de trong")
    private String password;
}
