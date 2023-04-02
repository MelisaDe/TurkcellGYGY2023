package kodlama.io.rentacar.business.dto.responses.update.maintenance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaintenanceResponse {
    private int id;
    private int carId;
}
