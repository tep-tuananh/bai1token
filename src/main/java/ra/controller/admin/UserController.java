package ra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Role;
import ra.model.entity.User;
import ra.repository.RoleRepositoty;
import ra.repository.UserRepository;
import ra.security.userDetailSecurity.UserPrincipal;
import ra.service.auth.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/admin/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepositoty roleRepositoty;

    @GetMapping("") // xong 36
    public ResponseEntity<List<User>> garAll() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/status/{userid}") // xong 39
    public ResponseEntity<?> updateStatus(@PathVariable Long userid) {
        User user = userRepository.findUserById(userid);
        if (user != null) {
            user.setStatus(!user.getStatus());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tồn tại mã người dùng này", HttpStatus.OK);
    }
    @GetMapping("/role") //  xong 40
    public ResponseEntity<List<Role>> getAllRole() {
        List<Role> roleList = roleRepositoty.findAll();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @GetMapping("/search") // xong 41
    public ResponseEntity<?> getName(@RequestParam("search") String search) {
        List<User> users = userRepository.findUserByFullName(search);
        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tồn tại tên người dùng này", HttpStatus.OK);
    }
}
