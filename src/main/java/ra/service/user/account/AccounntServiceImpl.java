package ra.service.user.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.reponse.AccountReqonse;
import ra.model.dto.request.AccountRequest;
import ra.model.entity.User;
import ra.repository.UserRepository;

import java.util.Objects;

@Service
public class AccounntServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(AccountRequest accountRequest,User user) {
        return userRepository.save(accountRequest.updateAccount(accountRequest,user));
    }


    @Override
    public AccountReqonse getById(Long id) {
        AccountReqonse account = new AccountReqonse();
        account.getInfor(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
        return account;
    }
}
