package ra.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ra.model.dto.reponse.UserReponse;
import ra.model.dto.request.UserLoginRequest;
import ra.model.entity.Role;
import ra.model.entity.User;
import ra.repository.RoleRepositoty;
import ra.repository.UserRepository;
import ra.security.jwt.JwtProvider;
import ra.security.userpincipal.UserPrincipel;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepositoty roleRepositoty;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public UserReponse login(UserLoginRequest userLogin) {
        Authentication authentication;
        try {
            authentication= authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Sai tai khoan, mat khau");
        }
        UserPrincipel userPrincipel =(UserPrincipel) authentication.getPrincipal();
        // tao ra token
        String token = jwtProvider.generateToken(userPrincipel);
        // converst sang doi tuong userReponse
        return UserReponse.builder()
                .fullName(userPrincipel.getUser().getFullName())
                .id(userPrincipel.getUser().getId())
                .token(token)
                .roles(userPrincipel.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }
    @Override
    public User register(User user) {
        // ma hoa mat khau
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // mac dinh tai khoan laf user
        Role role = roleRepositoty.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
