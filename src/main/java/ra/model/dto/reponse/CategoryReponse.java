package ra.model.dto.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.model.entity.Category;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryReponse {
    private Long id;
    private String categoryName;
    private  Boolean status;

    public CategoryReponse(Category category){
        this.id=category.getId();
        this.categoryName=category.getCategoryName();
        this.status=category.getStatus();
    }
}
