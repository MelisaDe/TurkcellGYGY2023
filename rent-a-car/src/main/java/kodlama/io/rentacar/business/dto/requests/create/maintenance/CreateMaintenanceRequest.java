package kodlama.io.rentacar.business.dto.requests.create.maintenance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {
    private int carId;
}