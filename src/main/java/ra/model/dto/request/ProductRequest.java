package ra.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.Category;
import ra.model.entity.Product;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductRequest {
    @NotNull(message = "Khong de trong ma code")
    @Size(min = 1, max = 100, message = "Toi da 100 ky tu")
    @Column(unique = true)
    private String sku;
    @NotNull(message = "Khong bo trong ten san pham")
    @Size(min = 1, max = 100, message = "Toi da 100 ky tu")
    private String productName;
    @NotNull(message = "Khong de trong gia tien")
    private String description;
    @NotNull(message = "khong de trong gia san pham")
    @Min(value = 1, message = "Gia lon hon 0")
    private Double price;
    @NotNull(message = "Khong bo trong so luong")
    @Min(value = 1, message = "Hang ton hon hon 0")
    private Integer quantity;
    @NotNull(message = "Khong de trong ten hinh anh")
    @Size(min = 1, max = 100, message = "Toi da 100 ky tu")
    private String image;
    private Boolean status = true;
    @NotNull(message = "Khong de trong ma danh  muc")
    private Long categoryId;

    public Product add(ProductRequest productRequest) {
        return Product.builder()
                .sku(productRequest.sku)
                .productName(productRequest.productName)
                .description(productRequest.description)
                .price(productRequest.price)
                .quantity(productRequest.quantity)
                .image(productRequest.image)
                .status(productRequest.status)
                .category(Category.builder().id(productRequest.categoryId).build())
                .build();
    }
}
