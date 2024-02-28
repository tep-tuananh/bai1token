package ra.controller.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.reponse.AccountReqonse;
import ra.model.dto.request.AccountRequest;
import ra.model.dto.request.AddressRequest;
import ra.model.dto.request.PasswordRequest;
import ra.model.entity.Address;
import ra.model.entity.User;
import ra.repository.AddressRepository;
import ra.repository.UserRepository;
import ra.security.userDetailSecurity.UserPrincipal;
import ra.service.auth.UserService;
import ra.service.user.account.AccountService;
import ra.service.user.address.AddressService;

import java.util.List;

@RestController
@RequestMapping("/v1/user/account")
public class AcccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;

    // thêm 1 đơn đặt hàng
    @GetMapping("") // xong 22
    public ResponseEntity<?> getbyid() {
        AccountReqonse account = accountService.getById(getUserId());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    @PutMapping("/update") // xong 23
    public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountRequest accountRequest, User user) {
        user = userRepository.findUserById(getUserId());
        if (user != null) {
            accountService.save(accountRequest, user);
            AccountReqonse account = accountService.getById(getUserId());
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Loi cap nhat", HttpStatus.OK);
        }
    }

    @PutMapping("/updatePassword") // xong 24
    public ResponseEntity<String> update_password(@RequestBody @Valid PasswordRequest passwordRequest) {
        User user = userService.findById(getUserId());
        String oldPassword = user.getPassword();
        boolean isExit = passwordEncoder.matches(passwordRequest.getOldPassword(), oldPassword);
        if (isExit) {
            if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())) {
                return new ResponseEntity<>("Nhập lại mật khẩu không chính xác", HttpStatus.OK);
            }
            user.setPassword(passwordEncoder.encode(passwordRequest.newPassword));
            userRepository.save(user);
            return new ResponseEntity<>("Cập nhật mật khẩu mới thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Nhập mật khẩu cũ  không chính  xác", HttpStatus.OK);
        }
    }

    @PostMapping("/address") // xong 25
    public ResponseEntity<?> add_address(@RequestBody @Valid AddressRequest addressRequest) {
        Address addressNew = addressRepository.save(addressRequest.add(addressRequest));
        return new ResponseEntity<>(addressNew, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idAddress}") // xong 26
    public ResponseEntity<?> delete(@PathVariable Long idAddress) {
        addressRepository.deleteById(idAddress);
        return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
    }

    @GetMapping("/address") // xong 27
    public ResponseEntity<List<Address>> getByRess() {
       List<Address> address = addressService.getByAddress(getUserId());
        return new ResponseEntity<>(address,HttpStatus.OK);
    }
    @GetMapping("/address/{id}") //xong 28
    public  ResponseEntity<?> get_address( @PathVariable Long id){
        Address address = addressService.findById(id);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }
}
