package ra.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.entity.WishList;
import ra.security.userDetailSecurity.UserPrincipal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WishListRequest {
    //    @NotBlank(message = "Không được để trống")
//    private Long  userId;
    @NotNull(message = "Không được để trống")
    @Min(value = 1, message = "Mã sản phẩm lớn hơn 0")
    private Long productId;

    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    public WishList add(WishListRequest wishListRequest) {
        // this.userId = getUserId();
        this.productId = wishListRequest.productId;
        return WishList.builder()
                .user(User.builder().id(getUserId()).build())
                .product(Product.builder().id(this.productId).build())
                .build();
    }
}
