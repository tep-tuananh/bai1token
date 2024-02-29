package ra.model.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.WishList;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WishListResponse {
    private Long id;
    private Long userId;
    private Long productId;

    public WishListResponse show(WishList wishList) {
        return WishListResponse.builder()
                .id(wishList.getId())
                .userId(wishList.getUser().getId())
                .productId(wishList.getProduct().getId())
                .build();
    }
}
