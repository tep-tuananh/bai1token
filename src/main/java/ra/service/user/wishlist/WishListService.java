package ra.service.user.wishlist;

import ra.model.entity.WishList;

import java.util.List;

public interface WishListService {
    List<WishList> getAll();
    WishList save(WishList wishList);
    void deleteById(Long id);
}
