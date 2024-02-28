package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    @NotNull(message = "Khong de trong")
    private User user; // mã người nhận
    @NotNull(message = "Không được để trống địa chỉ nhận hàng")
    @NotBlank(message = "Khong duoc de trong dia chi nhan hang")
    private String full_address; // địa chỉ
    @NotNull(message = "Không được để trống số điện thoại nhận hàng")
    @Pattern(regexp = "0[0-9]{9}",message = "Khong dung dinh dang sdt")
    @NotBlank(message = "Khong duoc de trong o dien thoai")
    private String phone; // số điện thoại người nhận
    @NotNull(message = "Không được để trống tên người nhận hàng nhận hàng")
    @NotBlank(message = "Khong duoc de trong ten nguoi nhan")
    private String receive_name;// tên người nhận
}
