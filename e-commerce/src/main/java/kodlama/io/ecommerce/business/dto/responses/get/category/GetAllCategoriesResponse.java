package kodlama.io.ecommerce.business.dto.responses.get.category;

import kodlama.io.ecommerce.business.dto.responses.get.product.GetAllProductsResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoriesResponse {
    private int id;
    private String name;
    private List<GetAllProductsResponse> products;
}
