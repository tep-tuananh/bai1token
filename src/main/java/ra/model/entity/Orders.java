package ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Data
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serial_number;
    @NotNull(message = "Khong duoc de trong")
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;
    @NotNull(message = "Khong duoc de trong")
    private String userName;
    @Min(value = 1,message = "Tong tien hoa  don lon hon 0")
    private Double price; // tong tien
    @NotNull(message = "Khong duoc de trong")
    private String note;
    @NotNull(message = "Khong duoc de trong dia chi")
    private String address;
    @NotNull(message = "Khong duoc bo trong")
    @Pattern(regexp = "0[0-9]{9}",message = "Khong dung dinh dang sdt")
    @Column(unique = true)
    private String phoneOder;
    @NotNull(message = "Khong duoc de trong")
    private StatusEnum status;


}
