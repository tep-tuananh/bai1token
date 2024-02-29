package ra.service.user.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.dto.reponse.WishListResponse;
import ra.model.entity.WishList;
import ra.repository.WishListRepository;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public List<WishList> getAll() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList save(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    @Override
    public void deleteById(Long id) {
        wishListRepository.deleteById(id);
    }
}
