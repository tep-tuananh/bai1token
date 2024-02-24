package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Khong de trong ma code")
    @Size(min = 1,max = 100,message = "Toi da 100 ky tu")
    @Column(unique = true)
    private String sku;
    @NotNull(message = "Khong de trong ma code")
    @Size(min = 1,max = 100,message = "Toi da 100 ky tu")
    private String productName;
    @NotNull(message = "Khong de trong ma code")
    private String description;
    @NotNull(message = "khong de trong gia san pham")
    @Min(value = 1,message = "Gia lon hon 0")
    private Double price;
    @NotNull(message = "Khong dc de trong")
    @Min(value = 1,message = "Hang ton hon hon 0")
    private Integer quantity;
    @NotNull(message = "Khong de trong ma code")
    @Size(min = 1,max = 100,message = "Toi da 100 ky tu")
    private String image;
    private Boolean status=true;
    @ManyToOne
    @JoinColumn(name = "categoryId" ,referencedColumnName = "id")
    @JsonIgnore
    private Category category;

}
