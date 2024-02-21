package ra.model.entity;

import jakarta.persistence.*;
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
    private String fullName;
    @Column(unique = true)
    private String userName;
    private String password;
    private Boolean status = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role", // tao ra bang co ten user_role
            joinColumns = @JoinColumn(name = "user_id"), // tham chieu den id bang user
            inverseJoinColumns = @JoinColumn(name = "role_id") // tham chien den bang role
    )
    private Set<Role> roles;
}
