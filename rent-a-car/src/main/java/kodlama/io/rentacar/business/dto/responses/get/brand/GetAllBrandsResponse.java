package kodlama.io.rentacar.business.dto.responses.get.brand;

import kodlama.io.rentacar.business.dto.responses.get.model.GetAllModelsResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBrandsResponse {
    private int id;
    private String name;
    //private List<GetAllModelsResponse> models;
}
