package ra.controller.user;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.dto.request.WishListRequest;
import ra.model.entity.WishList;
import ra.repository.WishListRepository;
import ra.service.user.wishlist.WishListService;

import java.util.List;


@RestController
@RequestMapping("/v1/user/wish-list")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private WishListRepository wishListRepository;

    @GetMapping("")
    public ResponseEntity<?> getAll() { // xong 33
        List<WishList> wishLists = wishListService.getAll();
        if (wishLists != null) {
            return new ResponseEntity<>(wishLists, HttpStatus.OK);
        }
        return new ResponseEntity<>("Danh sách đang trống! Hãy thêm sản phẩm yêu thích của bạn", HttpStatus.OK);
    }

    @PostMapping("/add") // xong 34
    public ResponseEntity<?> add(@RequestBody @Valid WishListRequest wishListRequest) {
        WishList wishList = wishListRequest.add(wishListRequest);
        wishListService.save(wishList);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // xong 35
    public ResponseEntity<?> delete(@PathVariable Long id) {
        WishList wishList = wishListRepository.findById(id).orElse(null);
        if (wishList != null) {
            wishListService.deleteById(id);
            return new ResponseEntity<>("Đã xóa thành công!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tồn tại mã sản phẩm này !", HttpStatus.OK);
    }
}
