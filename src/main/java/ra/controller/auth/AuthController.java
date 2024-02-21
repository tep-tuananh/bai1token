package ra.controller.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.model.dto.reponse.UserReponse;
import ra.model.dto.request.UserLoginRequest;
import ra.model.entity.User;
import ra.service.auth.UserService;

@RestController
@RequestMapping("/v1/auth/")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user){
        User userNew= userService.register(user);
        if(user!=null) {
            return new ResponseEntity<>("dang ky thanh cong", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("dang ky that bai", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest userLogin){
        UserReponse userReponse= userService.login(userLogin);
        return  new ResponseEntity<>(userReponse,HttpStatus.OK);
    }
}
