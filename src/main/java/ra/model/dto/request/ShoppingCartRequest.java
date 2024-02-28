package ra.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ra.model.entity.Product;
import ra.model.entity.ShoppingCart;
import ra.model.entity.User;
import ra.security.userDetailSecurity.UserPrincipal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShoppingCartRequest {
  //  private Long user_id;
    @NotNull(message = "Khong duoc bo trong")
    @Min(value = 1,message = "Lon hon 0")
    private Long productId;
    @Min(value = 1,message = "Lon hon 0")
    private Integer quantity;

    public static Long getUserId() { // lay ra user_id dang nhap
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }
    public   ShoppingCart  add(ShoppingCartRequest shoppingCartRequest){
       // this.user_id= getUserId();
        this.productId  = shoppingCartRequest.getProductId();
        this.quantity = shoppingCartRequest.getQuantity();
        return ShoppingCart .builder()
                .user(User.builder().id(getUserId()).build())
                .product(Product.builder().id(getProductId()).build())
                .quantity(this.quantity)
                .build();
    }
}
