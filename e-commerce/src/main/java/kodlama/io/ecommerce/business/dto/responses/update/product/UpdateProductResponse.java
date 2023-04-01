package kodlama.io.ecommerce.business.dto.responses.update.product;

import kodlama.io.ecommerce.entities.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponse {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Status status;
}
