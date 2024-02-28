package ra.model.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountReqonse {
    private String fullname;
    private String email;
    private String phone;
    private String address;
    public AccountReqonse getInfor(User user){
        this.fullname = user.getFullName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        return AccountReqonse.builder()
                .fullname(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
