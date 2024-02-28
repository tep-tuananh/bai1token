package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serialNumber;
    @NotNull(message = "Khong duoc de trong")
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;
    @NotNull(message = "Khong duoc de trong")
    @NotBlank(message = "Khong duoc null")
    private String userName;
    @Min(value = 1,message = "Tong tien hoa  don lon hon 0")
    private Double price; // tong tien
    @NotNull(message = "Khong duoc de trong")
    @NotBlank(message = "Khong duoc null")
    private String note;
    @NotNull(message = "Khong duoc de trong dia chi")
    @NotBlank(message = "Khong duoc null")
    private String address;
    @NotNull(message = "Khong duoc bo trong")
    @NotBlank(message = "Khong duoc null")
    @Pattern(regexp = "0[0-9]{9}",message = "Khong dung dinh dang sdt")
    @Column(unique = true)
    private String phoneOder;
    @NotNull(message = "Khong duoc de trong")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
