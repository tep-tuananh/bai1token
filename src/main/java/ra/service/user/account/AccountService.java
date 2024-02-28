package ra.service.user.account;

import ra.model.dto.reponse.AccountReqonse;
import ra.model.dto.request.AccountRequest;
import ra.model.entity.User;

public interface AccountService {
    AccountReqonse getById(Long id);
    User save(AccountRequest accountRequest,User user);
}
