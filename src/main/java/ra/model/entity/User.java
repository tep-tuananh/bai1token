package ra.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Khong duoc de trong")
    @Size(min = 5 , max = 30,message = "Tu 5-30 ky tu")
    private String userName;
    @NotNull(message = "Khong duoc de trong")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "không đúng định dạng")
    private String email;
    @NotNull(message = "Khong duoc bo trong")
  @Size(min = 5, max = 30,message = "Tu 5- 30 ky tu")
    private String fullName;
    @NotNull(message = "Khong duoc bo trong")
    @Pattern(regexp = "0[0-9]{9}",message = "Khong dung dinh dang sdt")
    @Column(unique = true)
    private String phone;
    @NotNull(message = "khong duoc de trong")
    private String address;
    @NotNull(message = "Khong duoc bo trong")
    private String password;
    private Boolean status=true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role", // tao ra bang co ten user_role
            joinColumns = @JoinColumn(name = "id"), // tham chieu den id bang user
            inverseJoinColumns = @JoinColumn(name = "role_id") // tham chien den bang role
    )
    private Set<Role> roles;
}
