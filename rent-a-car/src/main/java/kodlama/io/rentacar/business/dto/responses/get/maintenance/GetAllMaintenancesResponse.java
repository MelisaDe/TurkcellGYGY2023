package kodlama.io.rentacar.business.dto.responses.get.maintenance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMaintenancesResponse {
    private int id;
    private int carId;
}
