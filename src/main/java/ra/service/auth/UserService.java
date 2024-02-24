package ra.service.auth;

import ra.model.dto.reponse.UserReponse;
import ra.model.dto.request.UserLoginRequest;
import ra.model.entity.User;

public interface UserService {
    // dang ky
    User register(User user);
    UserReponse login(UserLoginRequest userLogin);

    User findById(Long id);
}
