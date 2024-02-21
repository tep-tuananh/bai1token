package ra.model.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.Role;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserReponse {
    private Long id;
    private String fullName;
    private String token;
    private final String type ="Bearer";
    private Set<String> roles = new HashSet<>();
}
